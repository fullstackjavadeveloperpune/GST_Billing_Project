import axios from 'axios';
import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';
import api from './axiosConfig';

export const CreateInvoice = () => {

    let navigate = useNavigate()

    const [gstBill, setGstBill] = useState({
        custName:"",
        custAddress:"",
        custContactNumber:"",
        invoiceDate:"",
        invoiceAmount:"",
        custEmailId:"",
        custGSTNumber:""
    })

    const {custName, custAddress, custContactNumber, invoiceDate, invoiceAmount, custEmailId, custGSTNumber} = gstBill;

    const onInputChange =(e)=>{
        setGstBill({...gstBill, [e.target.name]: e.target.value});
    }

    const onSubmit = async(e)=>{

        e.preventDefault()

        await api.post("/gstbills/createnewinvoice", gstBill);

        navigate(`/show`)
    }
  return (
    <div>
        <form onSubmit={(e)=> onSubmit(e)}>

            <div>
                Customer Name<input type='text' name='custName' value={custName} onChange={(e)=> onInputChange(e)} />
            </div>

             <div>
                Customer Address<input type='text' name='custAddress' value={custAddress} onChange={(e)=> onInputChange(e)} />
            </div>

             <div>
                Customer Contact Number<input type='number' name='custContactNumber' value={custContactNumber} onChange={(e)=> onInputChange(e)} />
            </div>

             <div>
                Invoice Date<input type='date' name='invoiceDate' value={invoiceDate} onChange={(e)=> onInputChange(e)} />
            </div>

             <div>
                Invoice Amount<input type='number' name='invoiceAmount' value={invoiceAmount} onChange={(e)=> onInputChange(e)} />
            </div>

             <div>
                Customer Email<input type='email' name='custEmailId' value={custEmailId} onChange={(e)=> onInputChange(e)} />
            </div>

             <div>
                Customer GST Number<input type='text' name='custGSTNumber' value={custGSTNumber} onChange={(e)=> onInputChange(e)} />
            </div>

            <button type='submit' className='btn btn-success'>Create Bill</button>

        </form>
    </div>
  )
}
