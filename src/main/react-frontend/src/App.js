import React from 'react';
import logo from './logo.svg';
import './App.css';
import UserComponent from './components/UserComponent';

function App() {
  return (
    <div className="App">
      <UserComponent />
    </div>
  );
}

export default App;

// import React, {useState, useEffect} from 'react';
// import logo from './logo.svg';
// import './App.css';

// function App() {
//     const [message, setMessage] = useState("");
//     useEffect(() => {
//         fetch('/users')
//             .then(response => response.text())
//             .then(message => {
//                 setMessage(message);
//             });
//     }, [])
//     return (
//         < div
//     className = "App" >
//         < header
//     className = "App-header" >
//         < img
//     src = {logo}
//     className = "App-logo"
//     alt = "logo" / >
//         < h1
//     className = "App-title" > {message} < /h1>
//         < /header>
//         < p
//     className = "App-intro" >
//         To
//     get
//     started, edit < code > src / App.js < /code> and save to reload.
//     < /p>
//     < /div>
// )
// }

// export default App;
