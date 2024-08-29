import React, { Component } from "react";
import { QRCodeCanvas } from "qrcode.react";
import { saveAs } from "file-saver";
import withRouter from "../helpers/withRouter";
import { connect } from "react-redux";
import { getStudentInfo } from "../redux/actions/studentAction";

class UserAccount extends Component {
  handleDownloadQRCode = () => {
    const canvas = document.getElementById("qrcode");
    canvas.toBlob((blob) => {
      saveAs(blob, "qrcode.png");
    });
  };
  componentDidMount = () => {
    const { id } = this.props.router.params;
    this.props.getStudentInfo(id);

    console.log("Did Mount");
  };
  render() {
    const {student} = this.props;
    return (
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
      </div>
    );
  }
}

// Styles for the component
const styles = {
  container: {
    padding: "20px",
    border: "1px solid #ddd",
    borderRadius: "5px",
    width: "400px",
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
    textAlign: "center",
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
  isLoading: state.commonReducer.isLoading,
});

const mapDispatchToProps = {
  getStudentInfo,
};

export default withRouter(
  connect(mapStateToProps, mapDispatchToProps)(UserAccount)
);
