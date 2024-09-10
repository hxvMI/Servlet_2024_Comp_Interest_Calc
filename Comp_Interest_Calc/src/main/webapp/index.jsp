<%@ page import="com.example.comp_interest_calc.HistoryItem" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.example.comp_interest_calc.HistoryItemRepo" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Compound Interest Calculator</title>
<%--Need to increase version=# to see changes in CSS              VVV--%>
    <link rel="stylesheet" type="text/css" href="style.css?version=7">
</head>
<%--
for="PrincipleAmount" and id="PrincipleAmount" link to each other

type="number"       only allows numbers to be INPUT

name="principle"    is NAME of the DATA when it is SENT to server

placeholder="2000"  gray numbers that disapear

value=${rPrinciple} actual values in the input box
--%>
<body>
<h1>Compound Interest Calculator</h1>
<form method="post" action="/Calculate" class="container">

    <label for="PrincipleAmount">Principle Amount $</label>
    <input id="PrincipleAmount" type="number" name="principle" step="0.01" placeholder="15000" value=${rPrinciple}>
    <br>

    <label for="InterestRate">Interest Rate %</label>
    <input id="InterestRate" type="number" step="0.01" min="1" max="10000" name="interest" placeholder="15" value=${rInterest}>

    <label for="Years">Years #</label>
    <input id="Years" type="number" min="1" name="years" placeholder="10" value=${rYears}>

    <label for="TimesPerYear">Times per Year #</label>
    <input id="TimesPerYear" type="number" min="1" name="perYear" placeholder="5" value=${rPerYear}>

    <div class="checkbox-container center-checkboxes">
        <label for="DisplayHistory" id="DisplayHistoryText">Display History</label>
<%--                                                                    showHistory value from POST Request sent if not NULL and TRUE will apply "check" property to HTML--%>
        <input id="DisplayHistory" type="checkbox" name="history" <%= (request.getAttribute("showHistory") != null && (Boolean)request.getAttribute("showHistory")) ? "checked" : "" %>>

        <label for="DeleteHistory" id="DeleteHistoryText">Delete History</label>
        <input id="DeleteHistory" type="checkbox" name="delete">
    </div>

    <button type="reset" value="Reset">Reset</button>
    <button type="submit" id="Submit">Submit</button>
    
    <h2>${incomplete}</h2>
    <h2>${compoundInterest}</h2>
</form>

<hr>

<h2>Calculation History</h2>

<form method="get" action="/webapi/HistoryResource/XML" class="container">
    <button id="DownloadXML" type="submit">Download XML</button>
</form>

<form method="get" action="/webapi/HistoryResource/JSON" class="container">
    <button id="DownloadJSON" type="submit">Download JSON</button>
</form>

<div class="history-table">
    <table>
        <thead>
        <tr>
            <th>#</th>
            <th>Principle Amount ($)</th>
            <th>Interest Rate (%)</th>
            <th>Years</th>
            <th>Times per Year</th>
            <th>Result ($)</th>
        </tr>
        </thead>
        <tbody><%--showHistory value from POST Request sent with SUBMIT         is TRUE if conditions MET--%>
        <% if (request.getAttribute("showHistory") != null && (Boolean)request.getAttribute("showHistory")) {
            HashMap<Integer, HistoryItem> historyItems = HistoryItemRepo.getItems();
            for(Map.Entry<Integer, HistoryItem> entry : historyItems.entrySet()) {
                HistoryItem currItem = entry.getValue();
        %>
        <tr>
            <td><%= entry.getKey() %></td>
            <td><%= currItem.getPrincipleD() %></td>
            <td><%= currItem.getInterestPercentD() %></td>
            <td><%= currItem.getYearsInt() %></td>
            <td><%= currItem.getPerYearInt() %></td>
            <td><%= currItem.getResult() %></td>
        </tr>
        <% }
        }
        %>
        </tbody>
    </table>
</div>

</body>
</html>
