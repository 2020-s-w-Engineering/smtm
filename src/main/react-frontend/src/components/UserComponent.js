import React from 'react';
import UserService from '../services/UserService'

class UserComponent extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            users: []
        }
    }

    componentDidMount() {
        UserService.getUsers().then((response) => {
            this.setState({users: response.data})
        });
    }

    render() {
        return (
            <div>
                <button onClick={UserService.createUser}>createUser</button>
                {this.componentDidMount}
                <h1 className = "text-center" > Users </h1>
                <table className = "table table-striped" >
                    <thead>
                        <tr>
                            <td> User Id </td>
                            <td> Korean? </td>
                        </tr>
                    </thead>
                    <tbody>
                    {
                        this.state.users.map(
                            user =>
                        <tr key = {user.id}>
                            <td> {user.userId} </td>
                            <td> {user.korean ? "true" : "false"} </td>
                        </tr>
                        )
                    }
                    </tbody>
                </table>
            </div>
        )
    }

}
export default UserComponent