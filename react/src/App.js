import { Outlet } from "react-router-dom";
import NavBar from './components/navigation-bar/NavBar'
import './App.css';

// Outlet: component to render child route components
const App = () => {
  return (
    <div className="App">
      <NavBar />
      <Outlet />
    </div>
  );
}

export default App;
