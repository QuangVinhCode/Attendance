import "./App.css";
import DashboardPage from "./pages/DashboardPage";
import { BrowserRouter } from "react-router-dom";
import store from "./redux/store";
import { Provider } from "react-redux";
function App() {
  return (
    <div className="App">
      <Provider store={store}>
        <BrowserRouter>
          <DashboardPage></DashboardPage>
        </BrowserRouter>
      </Provider>
    </div>
  );
}

export default App;
