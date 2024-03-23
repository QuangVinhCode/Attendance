import AuthService from "../services/auth.service";
import { useNavigate } from "react-router-dom";
export default function Header() {
    const navigate = useNavigate();

    const handleLogout = () => {
        AuthService.logout();
        navigate("/login");
    };

    return (
        <header className="bg-black">
            <nav
                className="mx-auto flex max-w-7xl items-center justify-between p-6"
                aria-label="Global"
            >
                <div className="flex lg:flex-1">
                    <a href="/" className="-m-1.5 p-1.5">
                        <span className="sr-only">Your Company</span>
                        <img
                            className="h-8 w-auto"
                            src="https://tailwindui.com/img/logos/mark.svg?color=indigo&shade=600"
                            alt=""
                        />
                    </a>
                </div>

                <div className="flex">
                    <button
                        onClick={handleLogout}
                        className="text-sm font-semibold leading-6 bg-blue-600 p-2 rounded-lg text-white"
                    >
                        Logout <span aria-hidden="true">&rarr;</span>
                    </button>
                </div>
            </nav>
        </header>
    );
}
