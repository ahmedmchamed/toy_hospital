import React, {Component} from 'react';
import './form.css';

class ToyForm extends Component {

    constructor(props) {
        super();
        this.state = {
            customerName: "ay",
            customerEmail: "ay",
            customerPhoneNumber: "ay",
            customerAddress: "ay",
            customerPhotos: null,
            customerToys: [],
            toyName: "ay",
            toyType: "ay",
            toyAge: 1,
            toySize: 1.0,
            customerRepairDescription: "ya"
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
        const customerPostUrl = "http://localhost:8080/api/customers";
        const toyPostUrl = "http://localhost:8080/api/toys";
        const photoPostUrl = "http://localhost:8080/api/upload";

        let files = new FormData();
        for (const photo of this.state.customerPhotos) {
            files.append("files", photo)
            files.append("toy", null)
        }

        const filesJson = JSON.stringify(files);
        const filesBlob = new Blob([filesJson], {
            type: "application/json"
        });

        let customer = new FormData();
        customer.append("customerName", this.state.customerName)
        customer.append("customerEmail", this.state.customerEmail)
        customer.append("customerPhoneNumber", this.state.customerPhoneNumber)
        customer.append("customerAddress", this.state.customerAddress)

        let customerBlob = new Blob([customer], {
            type: "application/json"
        });

        let toy = new FormData();
        toy.append("toyName", this.state.toyName)
        toy.append("toyType", this.state.toyType)
        toy.append("toyAge", this.state.toyAge)
        toy.append("toySize", this.state.toySize)
        toy.append("repairFromCustomer", this.state.customerRepairDescription)
        toy.append("customer", null)

        let toyBlob = new Blob([toy], {
            type: "application/json"
        });

        const toyTest = [
            {
                "toyName": this.state.toyName,
                "toyType": this.state.toyType,
                "toyAge": this.state.toyAge,
                "toySize": this.state.toySize,
                "repairFromCustomer": this.state.customerRepairDescription,
                "customer": null  
            },
            {
                "toyName": this.state.toyName,
                "toyType": this.state.toyType,
                "toyAge": this.state.toyAge,
                "toySize": this.state.toySize,
                "repairFromCustomer": this.state.customerRepairDescription,
                "customer": null  
            }
        ]
        
        fetch(customerPostUrl, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "toys": toyTest,
                "customer": {
                    "customerName": this.state.customerName,
                    "customerEmail": this.state.customerEmail,
                    "customerPhoneNumber": this.state.customerPhoneNumber,
                    "customerAddress": this.state.customerAddress
                }
                // "photos": files
            })
        })

        // fetch(customerPostUrl, {
        //     method: "POST",
        //     headers: {
        //         "Content-Type": "application/json"
        //     },
        //     body: JSON.stringify({
        //         "customerName": this.state.customerName,
        //         "customerEmail": this.state.customerEmail,
        //         "customerPhoneNumber": this.state.customerPhoneNumber,
        //         "customerAddress": this.state.customerAddress
        //     })
        // })
        // .then(customerJsonRes => customerJsonRes.json())
        // .then(addedCustomer => {
        //     fetch(toyPostUrl, {
        //         method: "POST",
        //         headers: {
        //             "Content-Type": "application/json"
        //         },
        //         body: JSON.stringify({
        //             "toyName": this.state.toyName,
        //             "toyType": this.state.toyType,
        //             "toyAge": this.state.toyAge,
        //             "toySize": this.state.toySize,
        //             "repairFromCustomer": this.state.customerRepairDescription,
        //             "customer": addedCustomer
        //         })
        //     })
        //     .then(toyJsonRes => toyJsonRes.json())
        //     .then(addedToy => {
        //         console.log(addedToy.id)
        //         files.append("toy", addedToy.id)
        //         fetch(photoPostUrl, {
        //             method: "POST",
        //             body: files
        //         })
        //     })
        // })
    }

    render() {
        return (
            <>
            <div className="toy-form">
                <form onSubmit={this.handleSubmit}>
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
                        <select value={this.state.toyType} defaultValue="default" onChange={this.handleToyType}>
                            <option disabled value="default">Please choose your toy type...</option>
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
                        <input type="file" name="files" id="customer-photo-upload" ref={this.fileUpload} onChange={this.handleFileUpload} multiple></input>

                        <input type="submit" value="Submit"/>
                    </div>
                </form>
            </div>
            {/* </div> */}
            </>
        )
    }

}

export default ToyForm;