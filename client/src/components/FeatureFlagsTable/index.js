import React, { useState, useEffect } from 'react';
import { Card, CardBody, Table, Collapse, Input, Button, CardFooter, CardHeader, InputGroupAddon, InputGroup } from 'reactstrap';
import axios from 'axios';
import './style.css';

const styles = {
  featureFlagsMgrHeader: {
    backgroundColor: 'navy',
    color: 'white',
    padding: '1em',
    textAlign: 'left',
    display: 'flex',
    fontWeight: 'bold'
  },
  mgrTitle: {
    flexGrow: '1'
  },
  mgrExpandCollapse: {
    flexShrink: '1'
  },
  cardActions: {
    float: "right"
  },
  cardFooter: {
    color: "red"
  },
  newFlagGroup: {
    width: "100%",
  },
  newFlagInput: {
    textAlign: "center"
  }

}

function FeatureFlagsTable() {
  const url = `http://localhost:8080/api/v1/featureflags` // hardcoding server url for now, can switch to .env later
  const [featureFlags, setFeatureFlags] = useState([]);
  const [isOpen, setIsOpen] = useState(true); // wireframes show an expand/collapse feature, so I decided to implement it
  const [dataDirty, setDataDirty] = useState(false);
  // using index instead of the flag name so we don't have to loop through all of the flags to find the active one
  const [activeFlagIndex, setActiveFlagIndex] = useState(-1);
  const [, setState] = useState();
  const [newFlagName, setNewFlagName] = useState("");
  const [errorText, setErrorText] = useState("");
  const [editMode, setEditMode] = useState("");

  function handleUpdate() {
    setState({});
  }

  // on initial render
  useEffect(() => {
    loadFlags();
  }, []);

  function loadFlags() {
    axios.get(url).then((response) => {
      setFeatureFlags(response.data);
      console.log("response.data", response.data);
      console.log(featureFlags);
    }).catch(() => {
      // for now, I won't be checking specific error codes, but can later
      setErrorText("Cannot connect to server. Please try again later.")
    });
  }

  function expandBits(featureIndex, value) {
    const cols = [];
    for (let i = 4; i >= 0; i--) {
      const mask = Math.pow(2, i);
      cols.push(<td key={i}><Input disabled={isFlagInactive(featureIndex)} onChange={() => onBitClick(featureIndex, mask)} type="checkbox" checked={mask & value} /></td>);
    }
    return cols;
  }

  function isFlagInactive(index) {
    return activeFlagIndex !== -1 && activeFlagIndex !== index;
  }

  function isFlagActive(index) {
    return activeFlagIndex !== -1 && activeFlagIndex === index;
  }

  function onBitClick(featureIndex, mask) {
    featureFlags[featureIndex].value ^= mask;
    setActiveFlagIndex(featureIndex);
    handleUpdate();
    setDataDirty(true);
    setEditMode("EXISTING");
  }

  function onSaveClick() {
    axios.post(url, featureFlags[activeFlagIndex]).then((response) => {
      setFeatureFlags(response.data);
      setDataDirty(false);
      setNewFlagName("");
      setActiveFlagIndex(-1);
    }).catch(() => {
      // for now, I won't be checking specific error codes, but can later
      setErrorText(`Cannot ${editMode === "EXISTING" ? "save" : "add"} flag. Please try again later.`)
    });
  }

  function onCancelClick() {
    setDataDirty(false);
    // fetch initial state again
    loadFlags();
    setActiveFlagIndex(-1);
  }

  function onExpandCollapseClick() {
    setIsOpen(!isOpen);
  }

  function flagClass(index) {
    return isFlagInactive(index) ? "flagInactive" : "";
  }

  function flagRowClass(index) {
    return isFlagActive(index) ? "flagRowActive flagRow" : "flagRow";
  }

  function handleNewFlagNameChange(event) {
    setNewFlagName(event.target.value);
  }

  function onNewFlagKeyPress(e) {
    if (e.charCode === 13) {
      addNewFlag();
    }
  }

  // microservice supports adding new flags, which get pushed to their array, so I feel we should support that workflow as well
  // however, it doesn't support remove a flag from db, so I can't include that workflow here
  function addNewFlag() {
    const newFlag = { name: newFlagName.trim(), value: 0 }
    featureFlags.push(newFlag);
    setNewFlagName("");
    setEditMode("NEW");
    setDataDirty(true);
    setActiveFlagIndex(featureFlags.length-1);
  }

  return (
    <Card>
      <CardHeader style={styles.featureFlagsMgrHeader}>
        <span style={styles.mgrTitle}>Feature Flag Manager</span>
        &nbsp;
        <span className="no-select" onClick={onExpandCollapseClick}>{isOpen ? '—' : '＋'}</span>
      </CardHeader>
      <Collapse isOpen={isOpen}>
        <CardBody>
          <Table color="dark">
            <thead>
              <tr>
                <th>Region</th>
                <th>Asia</th>
                <th>Korea</th>
                <th>Europe</th>
                <th>Japan</th>
                <th>America</th>
              </tr>
            </thead>
            <tbody>
              {
                featureFlags.map((flag, index) => {
                  return (
                    <tr key={flag.name} className={flagRowClass(index)}>
                      <td className={flagClass(index)}>{flag.name}</td>
                      {
                        expandBits(index, flag.value)
                      }
                    </tr>
                  )
                })
              }
              <tr>
                <td className="text-left">
                  <InputGroup style={styles.newFlagGroup}>
                    <Input style={styles.newFlagInput} value={newFlagName} onKeyPress={onNewFlagKeyPress} onChange={handleNewFlagNameChange} type="text" placeholder="Enter a new flag" />
                    <InputGroupAddon addonType="append">
                      {
                        newFlagName !== "" ? <Button style={styles.newFlagOk} onClick={addNewFlag} color="primary">Ok</Button> : null
                      }
                    </InputGroupAddon>
                  </InputGroup>
                  
                </td>
              </tr>
            </tbody>
          </Table>
          


        </CardBody>
        <CardFooter style={styles.cardFooter} className="clearfix">
          {errorText}
          {
              dataDirty === true ?
                (
                  <div style={styles.cardActions} className="clearfix">
                    <Button className="saveCancel" onClick={onCancelClick}>Cancel</Button>
                    <Button className="saveCancel" color="primary" onClick={onSaveClick}>Save</Button>
                  </div>
                )
                :
                null
            }
        </CardFooter>
      </Collapse>
    </Card>
  )
}

export default FeatureFlagsTable;