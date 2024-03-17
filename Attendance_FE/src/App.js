import { Route, Routes } from "react-router-dom";
import "./App.css";
import PrivateRoute from "./components/private-route";
import PublicRoute from "./components/public-route";
import Login from "./pages/auth/Login";
import Register from "./pages/auth/Register";
import Home from "./pages/home";

function App() {
    return (
        <Routes>
            <Route element={<PrivateRoute />}>
                <Route path="/" element={<Home />} />
            </Route>
            <Route element={<PublicRoute />}>
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
            </Route>
        </Routes>
    );
}

export default App;
