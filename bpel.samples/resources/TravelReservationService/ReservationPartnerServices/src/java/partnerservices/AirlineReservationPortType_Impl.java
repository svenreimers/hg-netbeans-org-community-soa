/*
 * Copyright (c) 2010, Oracle. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * * Neither the name of Oracle nor the names of its contributors
 *   may be used to endorse or promote products derived from this software without
 *   specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
package partnerservices;

import javax.ejb.SessionBean;
import partnerservices.callback.ReservationCallbackProviderBean;

public class AirlineReservationPortType_Impl implements partnerservices.AirlineReservationPortType, SessionBean {
    
    javax.ejb.SessionContext context;
    public void reserveAirline(javax.xml.soap.SOAPElement itinerary) throws
            java.rmi.RemoteException {
        
        String uniqueID = PartnerUtils.getUniqueID(itinerary);
        
        PartnerUtils.sendJMSMessageToReservationCallbackProviderDestination(
                ReservationCallbackProviderBean.AIRLINE_RESERVATION, uniqueID);
    }
    
    public boolean cancelAirline(javax.xml.soap.SOAPElement itinerary) throws
            java.rmi.RemoteException {
        
        boolean _retVal = false;
        return _retVal;
    }
    
    /**
     * @see javax.ejb.SessionBean#setSessionContext(javax.ejb.SessionContext)
     */
    public void setSessionContext(javax.ejb.SessionContext aContext) {
        context = aContext;
    }
    
    /**
     * @see javax.ejb.SessionBean#ejbActivate()
     */
    public void ejbActivate() {
    }
    
    /**
     * @see javax.ejb.SessionBean#ejbPassivate()
     */
    public void ejbPassivate() {
    }
    
    /**
     * @see javax.ejb.SessionBean#ejbRemove()
     */
    public void ejbRemove() {
    }
    
    /**
     * See section 7.10.3 of the EJB 2.0 specification
     * See section 7.11.3 of the EJB 2.1 specification
     */
    public void ejbCreate() {
    }
}
