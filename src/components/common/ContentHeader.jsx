import { Divider } from "antd";
import React, { Component } from "react";
import { IoMdArrowBack } from "react-icons/io";

export default class ContentHeader extends Component {
  render() {
    const { navigate, title, className } = this.props;
    return (
      <>
        <div className={className} style={{ marginLeft: 0 }}>
          <IoMdArrowBack
            style={{ cursor: "pointer" }}
            onClick={() => navigate(-1)}
          />
          <span>{title}</span>
        </div>
        <Divider></Divider>
      </>
    );
  }
}
