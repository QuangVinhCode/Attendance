import React from "react";
import AuthService from "../services/auth.service";
import { Outlet, Navigate } from "react-router-dom";

const PrivateRoute = () => {
    return AuthService.isLogin() ? <Outlet /> : <Navigate to={"/login"} />;
};

export default PrivateRoute;
