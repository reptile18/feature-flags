import React, { useState, useEffect} from 'react';
import { Card, CardBody, CardText, CardTitle, Table, Collapse, Input } from 'reactstrap';
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
  }
}

function FeatureFlagsTable() {
  const url=`http://localhost:8080/api/v1/featureflags`
  const urlPieces=`${process.env.REACT_APP_SERVER_URL}${process.env.REACT_APP_REST_VERS}/featureflags`
  const [featureFlags, setFeatureFlags] = useState([]);
  const [isOpen, setIsOpen] = useState(true);

  // on initial render
  useEffect(() => {
    axios.get(url).then((response) => {
      setFeatureFlags(response.data);
      console.log("response.data", response.data);
      console.log(featureFlags);
    });
  }, []);

  function expandBits(featureIndex, value) {
    const cols = [];
    for (let i = 4; i >= 0; i--) {
      const mask = Math.pow(2,i);
      //const flagValue = featureFlags[featureIndex].value;
      // feature is on
      //console.log(`val(${value}) & mask(${mask}): ${value&mask}`);
      cols.push(<td><Input onClick={() => onBitClick(featureIndex,mask)} type="checkbox" checked={mask&value} /></td>);
    }
    return cols;
  }

  function onBitClick(featureIndex, mask) {
    console.log(`val(${featureFlags[featureIndex].value}) ^ mask(${mask}) : ${featureFlags[featureIndex].value^mask}`)
    featureFlags[featureIndex].value ^= mask;
    axios.post(url,featureFlags[featureIndex]).then((response) => {
      setFeatureFlags(response.data);
    });
  }

  function onExpandCollapseClick() {
    setIsOpen(!isOpen);
  }

  return (
    <Card>
      <CardBody>
        <CardTitle style={styles.featureFlagsMgrHeader}>
          <span style={styles.mgrTitle}>Feature Flag Manager</span>
          &nbsp;
          <span className="no-select" onClick={onExpandCollapseClick}>{isOpen ? '—' : '＋'}</span>
        </CardTitle>
        <CardText>
          <Collapse isOpen={isOpen}>
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
                  featureFlags.map((flag,index) => {
                    return (
                      <tr>
                        <td>{flag.name}</td>
                        {
                          expandBits(index, flag.value)
                        }
                      </tr>
                    )
                  })
                }
              </tbody>
            </Table>
          </Collapse>
        </CardText>
      </CardBody>

    </Card>
  )
}

export default FeatureFlagsTable;