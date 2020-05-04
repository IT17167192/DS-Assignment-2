import React from "react";
import { MDBBreadcrumb, MDBBreadcrumbItem, MDBContainer } from "mdbreact";

const BreadcrumbPage = () => {
    return (
        <MDBContainer fluid>
            <MDBBreadcrumb light color="primary">
                <MDBBreadcrumbItem>Alarm Monitoring System</MDBBreadcrumbItem>
            </MDBBreadcrumb>
        </MDBContainer>
    );
};

export default BreadcrumbPage;
