import React, {useState} from 'react';
import './form.css';

const ToyForm = () => {

    let fileUpload = React.createRef();

    const [customerName, setCustomerName] = useState(null);
    const [customerEmail, setCustomerEmail] = useState("jacko@email.com");
    const [customerPhoneNumber, setCustomerPhoneNumber] = useState("012345670");
    const [customerAddress, setCustomerAddress] = useState("100 There Lane");
    const [customerPhotos, setCustomerPhotos] = useState([]);
    const [customerToys, setCustomerToys] = useState([]);
    const [toyName, setToyName] = useState("BearBones");
    const [toyType, setToyType] = useState("Bear");
    const [toyAge, setToyAge] = useState(1);
    const [toySize, setToySize] = useState(1);
    const [customerRepairDescription, setCustomerRepairDescription] = useState("It's broken. Very useful.");


    const handleCustomerName = (event) => {
        setCustomerName(event.target.value);
    }

    const handleCustomerEmail = (event) => {
        setCustomerEmail(event.target.value);
    }

    const handleCustomerPhoneNumber = (event) => {
        setCustomerPhoneNumber(event.target.value);
    }

    const handleCustomerAddress = (event) => {
        setCustomerAddress(event.target.value);
    }

    const handleToyName = (event) => {
        setToyName(event.target.value);
    }

    const handleToyType = (event) => {
        setToyType(event.target.value);
    }

    const handleToyAge = (event) => {
        setToyAge(event.target.value);
    }

    const handleToySize = (event) => {
        setToySize(event.target.value);
    }

    const handleRepairDescription = (event) => {
        setCustomerRepairDescription(event.target.value);
    }

    const handleFileUpload = () => {

        const allFiles = [
            ...customerPhotos,
            fileUpload.current.files
        ]
        
        setCustomerPhotos(allFiles);
        console.log(customerPhotos);
    }

    const handleSubmit = (event) => {
        // event.preventDefault();
        const customerPostUrl = "http://localhost:8080/api/customers";
        const toyPostUrl = "http://localhost:8080/api/toys";
        const photoPostUrl = "http://localhost:8080/api/upload";

        let files = new FormData();

        for (let i = 0; i < customerPhotos.length; i++) {
            for (let j = 0; j < customerPhotos[i].length; j++) {
                console.log(customerPhotos[i][j])
                files.append(`files[${i}][${j}]`, customerPhotos[i][j])
            }
        }

        const customerAndToy = {
            "toys": customerToys,
            "customer": {
                "customerName": customerName,
                "customerEmail": customerEmail,
                "customerPhoneNumber": customerPhoneNumber,
                "customerAddress": customerAddress
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
            setCustomerToys([]);
        })
    }
    
    const addToy = (event) => {
        event.preventDefault()

        setCustomerToys([...customerToys, {
            toyName: toyName,
            toyType: toyType,
            toyAge: toyAge,
            toySize: toySize,
            repairFromCustomer: customerRepairDescription
        }])
        console.log(customerToys)
    }

    let toyListItems = []

    if (customerToys.length > 0) {
        toyListItems = customerToys.map((toy, index) => {
            return (
                <li key={"toy_" + index}>{toy.toyName}</li>
            )
        })
    }

    return (
        <>
        <div className="toy-form">
            <form onSubmit={addToy} encType="multipart/form-data">
                <label htmlFor="customer-name">Customer name</label>
                <input type="text" id="customer-name" value={customerName} onChange={handleCustomerName} name="customer_name" />

                <label htmlFor="customer-email">Customer email</label>
                <input type="text" id="customer-email" value={customerEmail} onChange={handleCustomerEmail} name="email" />

                <label htmlFor="customer-phone-number">Customer phone number</label>
                <input type="text" id="customer-phone-number" value={customerPhoneNumber} onChange={handleCustomerPhoneNumber} name="phone_number" />

                <label htmlFor="customer-address">Customer this.ddress</label>
                <input type="text" id="customer-address" value={customerAddress} onChange={handleCustomerAddress} name="address" />

                <div className="toy-details-submission">
                    <label htmlFor="toy-name">Toy name</label>
                    <input type="text" id="toy-name" value={toyName} onChange={handleToyName} name="toy_name" />

                    <label htmlFor="toy-type">Toy type</label>
                    <select value={toyType} defaultValue="default" onChange={handleToyType} name="toy_type">
                        <option disabled value="default">Please choose your toy type...</option>
                        <option value="mechanical">Mechanical</option>
                        <option value="teddy">Teddy</option>
                        <option value="doll">Doll</option>
                        <option value="other">Other</option>
                    </select>

                    <label htmlFor="toy-age">Toy age</label>
                    <input type="number" id="toy-age" value={toyAge} onChange={handleToyAge} name="toy_age" />

                    <label htmlFor="toy-size">Toy size (cm)</label>
                    <input type="number" id="toy-size" value={toySize} onChange={handleToySize} name="toy_size" />

                    <label htmlFor="toy-repair-description">Describe the repairs needed</label>
                    <textarea id="toy-repair-description" value={customerRepairDescription} onChange={handleRepairDescription} name="repair_from_customer" ></textarea>
                
                    <label htmlFor="customer-photo-upload">Upload your photos</label>
                    <input type="file" name="files" id="customer-photo-upload" ref={fileUpload} onChange={handleFileUpload} multiple></input>
                    <input type="submit" value="ADD TOY"/>
                </div>
            </form>
            <button type="button" onClick={handleSubmit}>Submit toys</button>
        </div>
        <div>
            <ul>
                {toyListItems}
            </ul>
        </div>
        </>
    )

}

export default ToyForm;