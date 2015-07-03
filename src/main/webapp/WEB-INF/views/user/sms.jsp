<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    out.write("Done!");
/**
        Message ms = (Message) request.getAttribute("message");
        Sender s = (Sender) request.getAttribute("sender");
        Receiver r = (Receiver) request.getAttribute("receiver");

        TwiMLResponse twiml = new TwiMLResponse();
        com.twilio.sdk.verbs.Message sms =
                new com.twilio.sdk.verbs.Message("Message '" + ms.getBody() + "' by " + s.getFromNumber() + " sent on " + ms.getDateSend()+ " has been stored! Method: " + request.getMethod());
        try {
            twiml.append(sms);
        } catch (TwiMLException e) {
            e.printStackTrace();
        }

        response.setContentType("application/xml");
        response.getWriter().print(twiml.toXML());
**/
%>
