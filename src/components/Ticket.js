import React, {useState} from 'react';

const Ticket = () => {

    return (
        <div className = 'TicketWrapper'>
            <div className = 'TicketHeader'>
                <h1> Flight Ticket</h1>
            </div>
            <div className = 'TicketBody'>
                <div>
                <strong>Ticket ID:</strong> {ticket.ticket_id}
                </div>
                <div>
                    <strong>Flight ID:</strong> {ticket.flight_id}
                </div>
                <div>
                    <strong>Number of Travelers:</strong> {ticket.number_of_travelers}
                </div>
                <div>
                    <strong>Boarding Group:</strong> {ticket.boarding_group}
                </div>
                <div>
                    <strong>Total Price:</strong> ${ticket.total_price.toFixed(2)}
                </div>
                <div>
                    <strong>Ticket Date:</strong> {new Date(ticket.ticket_date).toLocaleString()}
                </div>

            </div>
        </div>
    )
}