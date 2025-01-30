import React, { Component } from "react";
import { QRCodeCanvas } from "qrcode.react";
import { saveAs } from "file-saver";
import withRouter from "../helpers/withRouter";
import { connect } from "react-redux";
import { getStudentInfo } from "../redux/actions/studentAction";
import { Button, Space, Table } from "antd";
import Column from "antd/lib/table/Column";
import { getClassesByStudent } from "../redux/actions/classAction";
import { IoEyeSharp } from "react-icons/io5";
import ClassDetail from "./ClassDetail";
import Header from "./Header";

class UserAccount extends Component {
  constructor() {
    super();
    this.state = {
      attendance: {},
      open: false, // Object to store selected dates for each account
    };
  }
  handleDownloadQRCode = () => {
    const canvas = document.getElementById("qrcode");
    canvas.toBlob((blob) => {
      saveAs(blob, "qrcode.png");
    });
  };

  openClassDetail = (object) => {
    this.setState({ attendance: object, open: true });
  };
  componentDidMount = () => {
    const { id } = this.props.router.params;
    this.props.getStudentInfo(id);
    this.props.getClassesByStudent(id);
    console.log("Did Mount");
  };
  render() {
    const { student, classes } = this.props;
    return (
      <>
        <Header />
        <div style={styles.container}>
          <h1>User Account Information</h1>
          <div style={styles.infoContainer}>
            <div style={styles.infoItem}>
              <strong>Username:</strong> {student.username}
            </div>
            <div style={styles.infoItem}>
              <strong>Email:</strong> {student.email}
            </div>
            <div style={styles.infoItem}>
              <strong>Full Name:</strong> {student.name}
            </div>
          </div>
          <div style={styles.qrContainer}>
            <QRCodeCanvas
              id="qrcode"
              value={student.username}
              size={128}
              level="H"
              includeMargin={true}
            />
            <button
              onClick={this.handleDownloadQRCode}
              style={styles.downloadButton}
            >
              Download QR Code
            </button>
          </div>
          <Table dataSource={classes} size="small" rowKey="id">
            <Column
              title="ID"
              key="id"
              dataIndex="id"
              width={40}
              align="center"
            ></Column>
            <Column
              title="Name"
              key="className"
              dataIndex="className"
              width={80}
              align="center"
            ></Column>
            <Column
              title="Action"
              key="action"
              dataIndex="action"
              width={40}
              align="center"
              render={(_, record) => (
                <Space size="middle">
                  <Button
                    key={record.key}
                    type="primary"
                    size="large"
                    onClick={() => this.openClassDetail(record)}
                  >
                    <IoEyeSharp style={{ marginRight: 8 }} />
                  </Button>
                </Space>
              )}
            ></Column>
          </Table>
          {this.state.open && (
            <ClassDetail
              id={this.state.attendance.id}
              open={this.state.open}
              onCancel={() => {
                this.setState({ ...this.state, attendance: {}, open: false });
              }}
            />
          )}
        </div>
      </>
    );
  }
}

// Styles for the component
const styles = {
  container: {
    padding: "20px",
    border: "1px solid #ddd",
    borderRadius: "5px",
    width: "800px",
    margin: "50px auto",
    textAlign: "left",
  },
  infoContainer: {
    marginTop: "20px",
  },
  infoItem: {
    marginBottom: "10px",
  },
  qrContainer: {
    marginTop: "20px",
    marginBottom: "100px",
  },
  downloadButton: {
    marginTop: "10px",
    padding: "10px 20px",
    border: "none",
    borderRadius: "5px",
    backgroundColor: "#4CAF50",
    color: "#fff",
    cursor: "pointer",
  },
};

const mapStateToProps = (state) => ({
  student: state.studentReducer.student,
  classes: state.classReducer.objects,
  isLoading: state.commonReducer.isLoading,
});

const mapDispatchToProps = {
  getStudentInfo,
  getClassesByStudent,
};

export default withRouter(
  connect(mapStateToProps, mapDispatchToProps)(UserAccount)
);
