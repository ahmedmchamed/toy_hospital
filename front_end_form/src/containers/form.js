import React, {Component} from 'react';
import './form.css';

class ToyForm extends Component {

    constructor(props) {
        super();
        this.state = {
            customerName: null,
            customerEmail: "jacko@email.com",
            customerPhoneNumber: "01234567",
            customerAddress: "100 Somewhere Lane",
            customerPhotos: [],
            customerToys: [],
            toyName: "Fluffles",
            toyType: "Bear",
            toyAge: 1,
            toySize: 1.0,
            customerRepairDescription: "Fix my fluffles"
        }
        this.fileUpload = React.createRef();

        this.addToy = this.addToy.bind(this)
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
        this.setState({ customerName: event.target.value })
    }

    handleCustomerEmail(event) {
        this.setState({ customerEmail: event.target.value })
    }

    handleCustomerPhoneNumber(event) {
        this.setState({ customerPhoneNumber: event.target.value })
    }

    handleCustomerAddress(event) {
        this.setState({ customerAddress: event.target.value })
    }

    handleToyName(event) {
        this.setState({ toyName: event.target.value })
    }

    handleToyType(event) {
        this.setState({ toyType: event.target.value })
    }

    handleToyAge(event) {
        this.setState({ toyAge: event.target.value })
    }

    handleToySize(event) {
        this.setState({ toySize: event.target.value })
    }

    handleRepairDescription(event) {
        this.setState({ customerRepairDescription: event.target.value })
    }

    handleFileUpload() {

        const allFiles = [
            ...this.state.customerPhotos,
            this.fileUpload.current.files
        ]
        
        this.setState({
            customerPhotos: allFiles
        }, () => {
            console.log(this.state.customerPhotos)
        })
    }

    handleSubmit(event) {
        // event.preventDefault();
        const customerPostUrl = "http://localhost:8080/api/customers";
        const toyPostUrl = "http://localhost:8080/api/toys";
        const photoPostUrl = "http://localhost:8080/api/upload";

        let files = new FormData();

        for (let i = 0; i < this.state.customerPhotos.length; i++) {
            for (let j = 0; j < this.state.customerPhotos[i].length; j++) {
                console.log(this.state.customerPhotos[i][j])
                files.append(`files[${i}][${j}]`, this.state.customerPhotos[i][j])
            }
        }

        const customerAndToy = {
            "toys": this.state.customerToys,
            "customer": {
                "customerName": this.state.customerName,
                "customerEmail": this.state.customerEmail,
                "customerPhoneNumber": this.state.customerPhoneNumber,
                "customerAddress": this.state.customerAddress
            }
        }

        const customerAndToyJson = JSON.stringify(customerAndToy)
        const customerAndToyBlob = new Blob([customerAndToyJson], {
            type: 'application/json'
        })

        files.append("json", customerAndToyBlob)

        fetch(customerPostUrl, {
            method: "POST",
            body: files
        })
        .then(res => res.json())
        .then(() => {
            this.setState({customerToys: []})
        })
    }
    
    addToy(event) {
        event.preventDefault()
        this.setState({
            customerToys: [...this.state.customerToys, {
                toyName: this.state.toyName,
                toyType: this.state.toyType,
                toyAge: this.state.toyAge,
                toySize: this.state.toySize,
                repairFromCustomer: this.state.customerRepairDescription
            }]
        })
        console.log(this.state.customerToys)
    }

    render() {

        let toyListItems = []

        if (this.state.customerToys.length > 0) {
            toyListItems = this.state.customerToys.map((toy, index) => {
                return (
                    <li key={"toy_" + index}>{toy.toyName}</li>
                )
            })
        }

        return (
            <>
            <div className="toy-form">
            {/*  */}
                <form onSubmit={this.addToy} encType="multipart/form-data">
                    <label htmlFor="customer-name">Customer name</label>
                    <input type="text" id="customer-name" value={this.state.customerName} onChange={this.handleCustomerName} name="customer_name" />

                    <label htmlFor="customer-email">Customer email</label>
                    <input type="text" id="customer-email" value={this.state.customerEmail} onChange={this.handleCustomerEmail} name="email" />

                    <label htmlFor="customer-phone-number">Customer phone number</label>
                    <input type="text" id="customer-phone-number" value={this.state.customerPhoneNumber} onChange={this.handleCustomerPhoneNumber} name="phone_number" />

                    <label htmlFor="customer-address">Customer address</label>
                    <input type="text" id="customer-address" value={this.state.customerAddress} onChange={this.handleCustomerAddress} name="address" />

                    <div className="toy-details-submission">
                        <label htmlFor="toy-name">Toy name</label>
                        <input type="text" id="toy-name" value={this.state.toyName} onChange={this.handleToyName} name="toy_name" />

                        <label htmlFor="toy-type">Toy type</label>
                        <select value={this.state.toyType} defaultValue="default" onChange={this.handleToyType} name="toy_type">
                            <option disabled value="default">Please choose your toy type...</option>
                            <option value="mechanical">Mechanical</option>
                            <option value="teddy">Teddy</option>
                            <option value="doll">Doll</option>
                            <option value="other">Other</option>
                        </select>

                        <label htmlFor="toy-age">Toy age</label>
                        <input type="number" id="toy-age" value={this.state.toyAge} onChange={this.handleToyAge} name="toy_age" />

                        <label htmlFor="toy-size">Toy size (cm)</label>
                        <input type="number" id="toy-size" value={this.state.toySize} onChange={this.handleToySize} name="toy_size" />

                        <label htmlFor="toy-repair-description">Describe the repairs needed</label>
                        <textarea id="toy-repair-description" value={this.state.customerRepairDescription} onChange={this.handleRepairDescription} name="repair_from_customer" ></textarea>
                    
                        <label htmlFor="customer-photo-upload">Upload your photos</label>
                        <input type="file" name="files" id="customer-photo-upload" ref={this.fileUpload} onChange={this.handleFileUpload} multiple></input>
                        <input type="submit" value="ADD TOY"/>
                    </div>
                </form>
                <button type="button" onClick={this.handleSubmit}>Submit toys</button>
            </div>
            <div>
                <ul>
                    {toyListItems}
                </ul>
            </div>
            </>
        )
    }

}

export default ToyForm;