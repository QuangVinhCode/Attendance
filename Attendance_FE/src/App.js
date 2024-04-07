import { BrowserRouter, Route, Routes } from "react-router-dom";
import "./App.css";
import PrivateRoute from "./components/private-route";
import PublicRoute from "./components/public-route";
import Login from "./pages/auth/Login";
import Register from "./pages/auth/Register";
import Home from "./pages/home";
import HomeClass from "./pages/homeClass";
import QRScanner from "./components/QRScanner";
import { Provider } from "react-redux";
import store from "./redux/store";
import DashboardPage from "./pages/DashboardPage";

function App() {
  return (
    <Provider store={store}>
      <BrowserRouter>
        <Routes>
          <Route element={<PrivateRoute />}>
            <Route path="/" element={<Home />} />
            <Route path="/class/:id" element={<HomeClass />} />
            <Route path="/attendance/:id" element={<QRScanner />} />
            <Route path="/dashboard/*" element={<DashboardPage />} />
          </Route>
          <Route element={<PublicRoute />}>
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </Provider>
  );
}

export default App;
