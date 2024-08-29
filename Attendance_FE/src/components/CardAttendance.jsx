import React from "react";
import { Link, useNavigate } from "react-router-dom";

const CardAttendance = ({ name, id, date }) => {
  const navigate = useNavigate();

  const openAttendance = (id) => {
    navigate("/attendance/" + id);
  };

  // Convert and format the date
  const formattedDate = new Date(date).toLocaleString("vi-VN", {
    timeZone: "Asia/Ho_Chi_Minh",
    hour: "2-digit",
    minute: "2-digit",
    day: "2-digit",
    month: "2-digit",
    year: "numeric",
  });

  return (
    <div className="max-w-3xl w-full mx-auto z-10">
      <div className="flex flex-col">
        <div className="bg-white border border-white shadow-lg rounded-3xl p-4 m-4">
          <div className="flex-none sm:flex">
            <div className="relative h-32 w-32 sm:mb-0 mb-3">
              <img
                alt=""
                src="https://plus.unsplash.com/premium_photo-1669652639356-f5cb1a086976?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8ZWR1Y2F0aW9ufGVufDB8MHwwfHx8MA%3D%3D"
                className="w-32 h-32 object-cover rounded-2xl"
              />
              <Link
                to=""
                className="absolute -right-2 bottom-2 -ml-3 text-white p-1 text-xs bg-green-400 hover:bg-green-500 font-medium tracking-wider rounded-full transition ease-in duration-300"
              >
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  viewBox="0 0 20 20"
                  fill="lime"
                  className="h-4 w-4"
                >
                  <path d="M13.586 3.586a2 2 0 112.828 2.828l-.793.793-2.828-2.828.793-.793zM11.379 5.793L3 14.172V17h2.828l8.38-8.379-2.83-2.828z"></path>
                </svg>
              </Link>
            </div>
            <div className="flex-auto sm:ml-5 justify-evenly">
              <div className="flex items-center justify-between sm:mt-2">
                <div className="flex items-center">
                  <div className="flex flex-col">
                    <div className="w-full flex-none text-lg text-gray-800 font-bold leading-none">
                      {name}
                    </div>
                  </div>
                </div>
              </div>
              <div className="flex flex-row items-center"></div>
              <div className="flex pt-2 text-sm text-gray-500">
                <div className="flex-1 inline-flex items-center">
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    className="h-5 w-5 mr-2"
                    viewBox="0 0 20 20"
                    fill="currentColor"
                  >
                    <path d="M8 9a3 3 0 100-6 3 3 0 000 6zM8 11a6 6 0 016 6H2a6 6 0 016-6zM16 7a1 1 0 10-2 0v1h-1a1 1 0 100 2h1v1a1 1 0 102 0v-1h1a1 1 0 100-2h-1V7z"></path>
                  </svg>
                  <p>{formattedDate}</p>
                </div>

                <button
                  className="flex-no-shrink bg-green-400 hover:bg-green-500 px-5 ml-4 py-2 text-xs shadow-sm hover:shadow-lg font-medium tracking-wider border-2 border-green-300 hover:border-green-500 text-white rounded-full transition ease-in duration-300"
                  onClick={() => openAttendance(id)}
                >
                  Mở
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CardAttendance;
