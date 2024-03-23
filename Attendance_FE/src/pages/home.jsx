import React, { useEffect, useState } from "react";
import AuthService, { URL_HTTP } from "../services/auth.service";
import CardClass from "../components/CardClass";
import Header from "../components/Header";
import withRouter from "../helpers/withRouter";
import axios from "axios";

const Home = () => {
    const [classes, setClasses] = useState([]);

    const getClasses = async () => {
        try {
            const url = URL_HTTP + `/classes`;
            const { data } = await axios.get(url);
            setClasses(data);
            console.log(data);
        } catch (error) {}
    };

    
    useEffect(() => {
        getClasses();
    }, []);
    return (
        <>
            <Header />
            <div className="flex flex-col  bg-gray-500 items-center min-h-screen justify-center">
                <div className="absolute  opacity-80  z-0"></div>

                {classes.map((item) => (
                    
                    <CardClass 
                        id={item.id}
                        user={item.teacher}
                        className={item.className}   
                    />
                ))}
            </div>
        </>
    );
};

export default Home;
