<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
   <%@ include file="HeaderForShopkart.jsp" %> 
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>Insert title here</title>
</head>
<body>
<div class="container">
	<div class="row">
        <div class="span8">
        <h1 align="center">Register Billing Address</h1>
    		<form:form method="post" action="/Shopkart/cart/createorder">
    			<div class="control-group">
    				<label for="apartmentnumber" class="control-label" >	
    					Apartment No:
    				</label>
    				<div class="controls">
    					<input name="apartmentnumber" type="text" style="width:500px" value="" placeholder="Apartment number" pattern=".{1,}" required="true">
    					
    				</div><br>
    			</div>
				<div class="control-group">
    				<label for="address" class="control-label">	
    					Street Address :
    				</label>
    				<div class="controls"><input name="streetname" placeholder=" Street" style="width:500px" type="text" value="" id="address" required="true" >
    				</div>
    			</div><br>
				
				<div class="control-group">
    				<label for="zip" class="control-label">	
    					Zip Code :
    				</label>
    				<div class="controls"><input name="zipcode" type="text" value="" id="zip" placeholder=" Zip code"style="width:500px" required="true" pattern=".{6,}">
    				</div>
    			</div><br>
				<div class="control-group">
    				<label for="city" class="control-label">	
    					City :
    				</label>
    				<div class="controls"><input name="city" type="text" value="" id="city" placeholder=" City" style="width:500px" required="true">
    				</div>
    			</div><br>
				<div class="control-group">
    				<label for="state" class="control-label">	
    					State :
    				</label>
    				<div class="controls"><input name="state" type="text" value="" id="state" placeholder=" State" style="width:500px"required="true">
    				</div>
    			</div><br>
				<div class="control-group">
    				<label for="country" class="control-label" >	
    					Country :
    				</label>
    				<div class="controls">
    					<select name="country" id="country" style="width:500px">
    						<option value=""></option>
    						<option value="AR">Argentina</option>
    						<option value="AU">Australia</option>
    						<option value="AT">Austria</option>
    						<option value="BY">Belarus</option>
    						<option value="BE">Belgium</option>
    						<option value="BA">Bosnia and Herzegovina</option>
    						<option value="BR">Brazil</option>
    						<option value="BG">Bulgaria</option>
    						<option value="CA">Canada</option>
    						<option value="CL">Chile</option>
    						<option value="CN">China</option>
    						<option value="CO">Colombia</option>
    						<option value="CR">Costa Rica</option>
    						<option value="HR">Croatia</option>
    						<option value="CU">Cuba</option>
    						<option value="CY">Cyprus</option>
    						<option value="CZ">Czech Republic</option>
    						<option value="DK">Denmark</option>
    						<option value="DO">Dominican Republic</option>
    						<option value="EG">Egypt</option>
    						<option value="EE">Estonia</option>
    						<option value="FI">Finland</option>
    						<option value="FR">France</option>
    						<option value="GE">Georgia</option>
    						<option value="DE">Germany</option>
    						<option value="GI">Gibraltar</option>
    						<option value="GR">Greece</option>
    						<option value="HK">Hong Kong S.A.R., China</option>
    						<option value="HU">Hungary</option>
    						<option value="IS">Iceland</option>
    						<option value="IN">India</option>
    						<option value="ID">Indonesia</option>
    						<option value="IR">Iran</option>
    						<option value="IQ">Iraq</option>
    						<option value="IE">Ireland</option>
    						<option value="IL">Israel</option>
    						<option value="IT">Italy</option>
    						<option value="JM">Jamaica</option>
    						<option value="JP">Japan</option>
    						<option value="KZ">Kazakhstan</option>
    						<option value="KW">Kuwait</option>
    						<option value="KG">Kyrgyzstan</option>
    						<option value="LA">Laos</option>
    						<option value="LV">Latvia</option>
    						<option value="LB">Lebanon</option>
    						<option value="LT">Lithuania</option>
    						<option value="LU">Luxembourg</option>
    						<option value="MK">Macedonia</option>
    						<option value="MY">Malaysia</option>
    						<option value="MT">Malta</option>
    						<option value="MX">Mexico</option>
    						<option value="MD">Moldova</option>
    						<option value="MC">Monaco</option>
    						<option value="ME">Montenegro</option>
    						<option value="MA">Morocco</option>
    						<option value="NL">Netherlands</option>
    						<option value="NZ">New Zealand</option>
    						<option value="NI">Nicaragua</option>
    						<option value="KP">North Korea</option>
    						<option value="NO">Norway</option>
    						<option value="PK">Pakistan</option>
    						<option value="PS">Palestinian Territory</option>
    						<option value="PE">Peru</option>
    						<option value="PH">Philippines</option>
    						<option value="PL">Poland</option>
    						<option value="PT">Portugal</option>
    						<option value="PR">Puerto Rico</option>
    						<option value="QA">Qatar</option>
    						<option value="RO">Romania</option>
    						<option value="RU">Russia</option>
    						<option value="SA">Saudi Arabia</option>
    						<option value="RS">Serbia</option>
    						<option value="SG">Singapore</option>
    						<option value="SK">Slovakia</option>
    						<option value="SI">Slovenia</option>
    						<option value="ZA">South Africa</option>
    						<option value="KR">South Korea</option>
    						<option value="ES">Spain</option>
    						<option value="LK">Sri Lanka</option>
    						<option value="SE">Sweden</option>
    						<option value="CH">Switzerland</option>
    						<option value="TW">Taiwan</option>
    						<option value="TH">Thailand</option>
    						<option value="TN">Tunisia</option>
    						<option value="TR">Turkey</option>
    						<option value="UA">Ukraine</option>
    						<option value="AE">United Arab Emirates</option>
    						<option value="GB">United Kingdom</option>
    						<option value="US">USA</option>
    						<option value="UZ">Uzbekistan</option>
    						<option value="VN">Vietnam</option>
    					</select>
    				</div>
    			</div><br><br>
				<div class="form-actions">
    				<button type="submit" class="btn btn-large btn-primary">Save Billing Address</button>
    			</div>
    		</form:form>
    	</div> 
	</div>
</div>
<script type="text/javascript">

</script>
</body>
</html>
				
				
				
				
				
     