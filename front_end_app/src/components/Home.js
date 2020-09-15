import React, {Component} from "react";

class Home extends Component {

    constructor(props) {
        super(props);
        //this.state will be all the toys
    }

    handleLogOut() {
        localStorage.clear();
        window.location.href = "/";
    }

    //fetch all toys on mount

    render() {
        return (
            <>
                <p>You've successfully logged in!</p>
                <button onClick={this.handleLogOut}>Log Out</button>
            </>
        )
    }
}

export default Home;