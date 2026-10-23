import axios from 'axios';
import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';

export const SignIn = () => {

    let navigate = useNavigate()

    const [user, setUser] = useState({
      
        userEmailId:"",
        userPassword:""
    })

    const { userEmailId, userPassword} = user;

    const onInputChange = (e)=>{
        setUser({...user, [e.target.name]: e.target.value});
    }

    const onSubmit = async(e)=>{

        e.preventDefault();

       const result= await axios.post("http://localhost:9999/auth/signin", user)

       localStorage.setItem("token", result.data);

       if(result.data){
        navigate(`/show`)
       }else{
        alert("Invalid Credentials")

        navigate(`/`)
       }

    }
  return (
    <div>

        <form onSubmit={(e)=> onSubmit(e)}>

         

              <div>
                User Email<input type='email' name='userEmailId' value={userEmailId} onChange={(e)=> onInputChange(e)} />
            </div>

              <div>
                User Password<input type='password' name='userPassword' value={userPassword} onChange={(e)=> onInputChange(e)} />
            </div>

            <button type='submit' className='btn btn-success'>SignIn</button>

        </form>

    </div>
  )
}
