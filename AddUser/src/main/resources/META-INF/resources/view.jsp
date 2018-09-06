<%@ include file="/init.jsp" %>

<portlet:actionURL name="addUser" var="addUser"/>

<h2>Add User</h2>

<form action="<%= addUser %>" name="<portlet:namespace/>addUser">
	<div class="form-group">
		<label>Screen Name</label>
		<input class="form-control" type="text" name="screenName"></input>
	</div>
	<div class="form-group">
		<label>First Name</label>
		<input class="form-control" type="text" name="firstName"></input>
	</div>
	<div class="form-group">
		<label>Last Name</label>
		<input class="form-control" type="text" name="LastName"></input>
	</div>
	<div class="form-group">
		<label>Email</label>
		<input class="form-control" type="email" name="email"></input>
	</div>
	<div class="form-group">
		<label>Temporary Password</label>
		<input class="form-control" type="password" name="tempPassword"></input>
	</div>
	<button class="btn btn-primary" type="submit">Submit</button>
</form>