import axios from 'axios';
import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';

export const SignUp = () => {

    let navigate = useNavigate()

    const [user, setUser] = useState({
        userName:"",
        userEmailId:"",
        userPassword:""
    })

    const {userName, userEmailId, userPassword} = user;

    const onInputChange = (e)=>{
        setUser({...user, [e.target.name]: e.target.value});
    }

    const onSubmit = async(e)=>{

        e.preventDefault();

        await axios.post("http://localhost:9999/auth/signup", user)

        navigate(`/`)

    }
  return (
    <div>

        <form onSubmit={(e)=> onSubmit(e)}>

            <div>
                User Name<input type='text' name='userName' value={userName} onChange={(e)=> onInputChange(e)} />
            </div>

              <div>
                User Email<input type='email' name='userEmailId' value={userEmailId} onChange={(e)=> onInputChange(e)} />
            </div>

              <div>
                User Password<input type='password' name='userPassword' value={userPassword} onChange={(e)=> onInputChange(e)} />
            </div>

            <button type='submit' className='btn btn-success'>SignUp</button>

        </form>

    </div>
  )
}
