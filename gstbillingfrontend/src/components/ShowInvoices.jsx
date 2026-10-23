import axios from 'axios'
import React, { useEffect, useState } from 'react'
import api from './axiosConfig'
import { Link, useNavigate } from 'react-router-dom'

export const ShowInvoices = () => {

    let navigate = useNavigate()

    const [gstBills, setGstBills] = useState([])

    useEffect(()=>{

        loadGSTBills()

    }, [])


    const loadGSTBills = async()=>{

        const result = await api.get("/gstbills/findall");

        setGstBills(result.data)

        console.log(result.data);
    }

    const deleteInvoice = async(invoiceId)=>{

        console.log("inside delete")
        await api.delete(`/gstbills/deletebyid/${invoiceId}`)

        console.log("after delete")
        loadGSTBills()
        
    }
  return (
    <div>

        <Link className='btn btn-info' to={`/create-invoice`}>Create New Bill</Link>
        <table className='table table-hover'>

            <thead>
                <th>Invoice ID</th>
                <th>Customer Name</th>
                <th>Customer Address</th>
                <th>Contact Number</th>
                <th>Invoice Date</th>
                <th>Invoice Amount</th>
                <th>Customer Email</th>
                <th>GST Number</th>
                <th>Action</th>
            </thead>

            <tbody>
                {
                    gstBills.map((gstBill)=>(
                        <tr>
                            <td>{gstBill.invoiceId}</td>
                            <td>{gstBill.custName}</td>
                            <td>{gstBill.custAddress}</td>
                            <td>{gstBill.custContactNumber}</td>
                            <td>{gstBill.invoiceDate}</td>
                            <td>{gstBill.invoiceAmount}</td>
                            <td>{gstBill.custEmailId}</td>
                            <td>{gstBill.custGSTNumber}</td>
                            <td>
                                <button className='btn btn-danger' onClick={()=> deleteInvoice(gstBill.invoiceId)} >Delete</button>
                                <Link className='btn btn-info'>Edit</Link>
                            </td>
                        </tr>
                    ))
                }
            </tbody>

        </table>
    </div>
  )
}
