import React, {useState, useEffect} from 'react';
import axios from "axios";

export const PurchaseHistory = () => {

    return(
        <div>
            <h2>Flight Schedule</h2>
            <table style={{borderCollapse: 'collapse', width: '80%', margin: '0 auto'}}>
                <thead>
                    <tr>
                        <th style={tableHeaderStyle}>Date</th>
                        <th style={tableHeaderStyle}>Depart</th>
                        <th style={tableHeaderStyle}>Origin</th>
                        <th style={tableHeaderStyle}>Destination</th>
                        <th style={tableHeaderStyle}>Arrival</th>
                        <th style={tableHeaderStyle}>Flight Number</th>
                        <th style={tableHeaderStyle}>Price</th>
                    </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
    )
}

const tableHeaderStyle = {
    border: '1px solid #ddd',
    padding: '8px',
    textAlign: 'center',
    fontWeight: 'bold',
  };