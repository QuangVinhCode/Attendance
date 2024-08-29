import React, { Component } from "react";
import axios from "axios";
import { Button, Input, Form, message } from "antd";
import { Navigate, useNavigate } from "react-router-dom";
import withRouter from "../helpers/withRouter";

class CreateAttendance extends Component {
  state = {
    name: "",
    date: null,
    classId: null,
  };

  handleSubmit = (values) => {
    console.log(values);

    axios
      .post("http://localhost:8080/api/v1/attendances", values)
      .then(() => {
        message.success("Attendance session created successfully!");

        this.props.router.navigate(-1);
      })
      .catch((error) => {
        console.error("Error creating attendance session:", error);
        message.error("Failed to create attendance session");
      });
  };

  render() {
    const { id } = this.props.router.params;
    return (
      <div style={styles.container}>
        <h1>Create Attendance Session</h1>
        <Form layout="vertical" onFinish={this.handleSubmit}>
          <Form.Item label="Name" name="name">
            <Input />
          </Form.Item>
          <Form.Item
            label="Class"
            name="class_id"
            initialValue={id}
            hidden={true}
          ></Form.Item>
          <Form.Item>
            <Button type="primary" htmlType="submit">
              Tạo
            </Button>
          </Form.Item>
        </Form>
      </div>
    );
  }
}

const styles = {
  container: {
    width: "400px",
    margin: "50px auto",
    padding: "20px",
    border: "1px solid #ddd",
    borderRadius: "5px",
    backgroundColor: "#fff",
  },
};

export default withRouter(CreateAttendance);
