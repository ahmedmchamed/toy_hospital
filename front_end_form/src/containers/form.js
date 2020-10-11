import React, {Component} from 'react';
import './form.css';

class ToyForm extends Component {

    constructor(props) {
        super();
        this.state = {
            customerName: "",
            customerEmail: "",
            customerPhoneNumber: "",
            customerAddress: "",
            customerPhotos: null,
            toyName: "",
            toyType: "",
            toyAge: 0,
            toySize: 0.0,
            customerRepairDescription: ""
        }
        this.fileUpload = React.createRef();

        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleCustomerName = this.handleCustomerName.bind(this);
        this.handleCustomerEmail = this.handleCustomerEmail.bind(this);
        this.handleCustomerPhoneNumber = this.handleCustomerPhoneNumber.bind(this);
        this.handleCustomerAddress = this.handleCustomerAddress.bind(this);
        this.handleToyName = this.handleToyName.bind(this);
        this.handleToyType = this.handleToyType.bind(this);
        this.handleToyAge = this.handleToyAge.bind(this);
        this.handleToySize = this.handleToySize.bind(this);
        this.handleRepairDescription = this.handleRepairDescription.bind(this);
        this.handleFileUpload = this.handleFileUpload.bind(this);
    }

    handleCustomerName(event) {
        this.setState({
            customerName: event.target.value
        })
    }

    handleCustomerEmail(event) {
        this.setState({
            customerEmail: event.target.value
        })
    }

    handleCustomerPhoneNumber(event) {
        this.setState({
            customerPhoneNumber: event.target.value
        })
    }

    handleCustomerAddress(event) {
        this.setState({
            customerAddress: event.target.value
        }, () => {console.log(this.state.customerPhoneNumber)})
    }

    handleToyName(event) {
        this.setState({
            toyName: event.target.value
        })
    }

    handleToyType(event) {
        this.setState({
            toyType: event.target.value
        })
    }

    handleToyAge(event) {
        this.setState({
            toyAge: event.target.value
        })
    }

    handleToySize(event) {
        this.setState({
            toySize: event.target.value
        })
    }

    handleRepairDescription(event) {
        this.setState({
            customerRepairDescription: event.target.value
        })
    }

    handleFileUpload() {
        this.setState({
            customerPhotos: this.fileUpload.current.files
        }, () => {
            console.log(this.state.customerPhotos)
        })
    }

    handleSubmit(event) {
        event.preventDefault();
        const customerPostUrl = "http://localhost:8080/customers";
        const toyPostUrl = "http://localhost:8080/toys";

        fetch(customerPostUrl, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "name": this.state.customerName,
                "email": this.state.customerEmail,
                "phoneNumber": this.state.customerPhoneNumber,
                "address": this.state.customerAddress
            })
        })
        .then(customerJsonRes => customerJsonRes.json())
        .then(addedCustomer => {
            fetch(toyPostUrl, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    "name": this.state.toyName,
                    "type": this.state.toyType,
                    "age": this.state.toyAge,
                    "size": this.state.toySize,
                    "repairFromCustomer": this.state.customerRepairDescription,
                    "customer": addedCustomer
                })
            })
        })
    }

    render() {
        return (
            <>
            <div class="toy-form">
                <form onSubmit={this.handleSubmit}>
                    <label htmlFor="customer-name">Customer name</label>
                    <input type="text" id="customer-name" value={this.state.customerName} onChange={this.handleCustomerName} name="customer_name" />

                    <label htmlFor="customer-email">Customer email</label>
                    <input type="text" id="customer-email" value={this.state.customerEmail} onChange={this.handleCustomerEmail} name="email" />

                    <label htmlFor="customer-phone-number">Customer phone number</label>
                    <input type="text" id="customer-phone-number" value={this.state.customerPhoneNumber} onChange={this.handleCustomerPhoneNumber} name="phone_number" />

                    <label htmlFor="customer-address">Customer address</label>
                    <input type="text" id="customer-address" value={this.state.customerAddress} onChange={this.handleCustomerAddress} name="address" />

                    <label htmlFor="toy-name">Toy name</label>
                    <input type="text" id="toy-name" value={this.state.toyName} onChange={this.handleToyName} name="toy_name" />

                    <label htmlFor="toy-type">Toy type</label>
                    <select value={this.state.toyType} onChange={this.handleToyType}>
                        <option value="mechanical">Mechanical</option>
                        <option value="teddy">Teddy</option>
                        <option value="doll">Doll</option>
                        <option value="other">Other</option>
                    </select>

                    <label htmlFor="toy-age">Toy age</label>
                    <input type="number" id="toy-age" value={this.state.toyAge} onChange={this.handleToyAge} name="age" />

                    <label htmlFor="toy-size">Toy size (cm)</label>
                    <input type="number" id="toy-size" value={this.state.toySize} onChange={this.handleToySize} name="size" />

                    <label htmlFor="toy-repair-description">Describe the repairs needed</label>
                    <textarea id="toy-repair-description" value={this.state.customerRepairDescription} onChange={this.handleRepairDescription} name="repair_from_customer" ></textarea>

                    <label htmlFor="customer-photo-upload">Upload your photos</label>
                    <input type="file" id="customer-photo-upload" ref={this.fileUpload} onChange={this.handleFileUpload} multiple></input>

                    <input type="submit" value="Submit"/>
                </form>
            </div>
            </>
        )
    }

}

export default ToyForm;