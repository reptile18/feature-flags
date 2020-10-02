import React from 'react';
import { Container, Row, Col } from 'reactstrap';
import './App.css';
import './components/FeatureFlagsTable';
import FeatureFlagsTable from './components/FeatureFlagsTable';

function App() {
  return (
    <div className="App">
      <Container>
        <Row>
          <Col lg="12">
            <FeatureFlagsTable />
          </Col>
        </Row>
      </Container>
      
    </div>
  );
}

export default App;
