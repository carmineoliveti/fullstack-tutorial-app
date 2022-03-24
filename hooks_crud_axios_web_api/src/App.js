import './App.css';
import "bootstrap/dist/css/bootstrap.min.css";
import Navbar from './components/Navbar';
import AddTutorial from './components/AddTutorial';
import { Route } from 'react-router-dom';
import ListTutorial from './components/ListTutorial';
import Tutorial from './components/Tutorial';
import { Switch } from 'react-router-dom';

function App() {
  return (
    <div className="App">
      <Navbar />
      <div className="container mt-3">
        <Switch>
          <Route exact path={["/", "/tutorials"]} component={ListTutorial} />
          <Route exact path="/add" component={AddTutorial} />
          <Route path="/tutorials/:id" component={Tutorial} />
        </Switch>
      </div>
    </div>
  );
}

export default App;
