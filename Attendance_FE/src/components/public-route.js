import React from "react";
import AuthService from "../services/auth.service";
import { Outlet, Navigate } from "react-router-dom";

const PublicRoute = () => {
    return !AuthService.isLogin() ? <Outlet /> : <Navigate to={"/"} />;
};

export default PublicRoute;
