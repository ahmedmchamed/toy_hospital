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

    handleSubmit() {

    }

    render() {
        return (
            <>
                <form onSubmit={this.handleSubmit}>
                    <label htmlFor="customer-name">Customer name</label>
                    <input type="text" id="customer-name" name="name" />

                    <label htmlFor="customer-email">Customer email</label>
                    <input type="text" id="customer-email" name="email" />

                    <label htmlFor="customer-phone-number">Customer phone number</label>
                    <input type="text" id="customer-phone-number" name="phone_number" />

                    <label htmlFor="customer-address">Customer address</label>
                    <input type="text" id="customer-address" name="address" />
                </form>
            </>
        )
    }

}

export default ToyForm;