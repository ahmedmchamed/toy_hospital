import React, {Component} from 'react';

class ToyForm extends Component {

    constructor(props) {
        super();
        this.state = {
            customerName: "",
            customerEmail: "",
            customerPhoneNumber: "",
            customerAddress: "",
            toyName: "",
            toyType: "",
            toyAge: 0,
            toySize: 0.0,
            customerRepairDescription: ""
        }

        this.handleSubmit = this.handleSubmit.bind(this);
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
        })
    }

    handleSubmit() {

    }

    render() {
        return (
            <>
            <div class="toy-form">
                <form onSubmit={this.handleSubmit}>
                    <label htmlFor="customer-name">Customer name</label>
                    <input type="text" id="customer-name" value={this.state.customerName} onChange={this.handleCustomerName} name="name" />

                    <label htmlFor="customer-email">Customer email</label>
                    <input type="text" id="customer-email" value={this.state.customerEmail} onChange={this.handleCustomerEmail} name="email" />

                    <label htmlFor="customer-phone-number">Customer phone number</label>
                    <input type="text" id="customer-phone-number" value={this.state.customerPhoneNumber} onChange={this.handleCustomerPhoneNumber} name="phone_number" />

                    <label htmlFor="customer-address">Customer address</label>
                    <input type="text" id="customer-address" value={this.state.customerAddress} onChange={this.handleCustomerAddress} name="address" />
                </form>
            </div>
            </>
        )
    }

}

export default ToyForm;