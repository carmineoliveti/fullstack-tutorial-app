import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Navbar from './components/Navbar';
import { Switch, Route, BrowserRouter as Router } from 'react-router-dom';
import TutorialsList from './components/TutorialsList';
import Tutorial from './components/Tutorial';
import AddTutorial from './components/AddTutorial';
import { Link } from 'react-router-dom';

function App() {
  return (
    <Router>
      <nav className="navbar navbar-expand navbar-dark bg-dark">
        <a href="/tutorials" className="navbar-brand">
          bezKoder
        </a>
        <div className="navbar-nav mr-auto">
          <li className="nav-item">
            <Link to={"/tutorials"} className="nav-link">
              Tutorials
            </Link>
          </li>
          <li className="nav-item">
            <Link to={"/add"} className="nav-link">
              Add
            </Link>
          </li>
        </div>
      </nav>
    <div className="container mt-3">
      <Switch>
        <Route exact path={["/", "/tutorials"]} component={TutorialsList} />
        <Route exact path="/add" component={AddTutorial} />
        <Route path="/tutorials/:id" component={Tutorial} />
      </Switch>
    </div>
    </Router>
  );
}

export default App;
