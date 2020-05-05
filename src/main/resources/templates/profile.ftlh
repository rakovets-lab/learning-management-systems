<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">

<@c.page>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="/static/css/profile.css" rel="stylesheet">
    <form method="post" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
        <div class="container">
            <div class="view-account">
                <section class="module">
                    <div class="module-inner">
                        <div class="side-bar">
                            <div class="user-info">
                                <img class="img-profile img-circle img-responsive center-block" src="https://bootdey.com/img/Content/avatar/avatar1.png" alt="">
                                <ul class="meta list list-unstyled">
                                    <li class="name"><#if user??>${user.username}</#if>
                                        <label class="label label-info">UX Designer</label>
                                    </li>
                                    <li class="email"><a href="#">${email!''}</a></li>
                                </ul>
                            </div>
                            <nav class="side-menu">
                                <ul class="nav">
                                    <li class="active"><a href="#"><span class="fa fa-user"></span> Profile</a></li>
                                    <li><a href="#"><span class="fa fa-cog"></span> Settings</a></li>
                                    <li><a href="#"><span class="fa fa-credit-card"></span> Billing</a></li>
                                    <li><a href="#"><span class="fa fa-envelope"></span> Messages</a></li>
                                    <li><a href="user-drive.html"><span class="fa fa-th"></span> Drive</a></li>
                                    <li><a href="#"><span class="fa fa-clock-o"></span> Reminders</a></li>
                                </ul>
                            </nav>
                        </div>
                        <div class="content-panel">
                            <h2 class="title">Profile<span class="pro-label label label-warning">PRO</span></h2>
                            <form class="form-horizontal">
                                <fieldset class="fieldset">
                                    <h3 class="fieldset-title">Personal Info</h3>
                                    <div class="form-group avatar">
                                        <figure class="figure col-md-2 col-sm-3 col-xs-12">
                                            <img class="img-rounded img-responsive" src="https://bootdey.com/img/Content/avatar/avatar1.png" alt="">
                                        </figure>
                                        <div class="form-inline col-md-10 col-sm-9 col-xs-12">
                                            <input type="file" class="file-uploader pull-left">
                                            <button type="submit" class="btn btn-sm btn-default-alt pull-left">Update Image</button>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-2 col-sm-3 col-xs-12 control-label">User Name</label>
                                        <div class="col-md-10 col-sm-9 col-xs-12">
                                            <input type="text" name="username" value=<#if user??>${user.username}</#if>
                                            class="form-control ${(usernameError??)?string('is-invalid', '')}">
                                            <#if usernameError??>
                                                <div class="invalid-feedback">
                                                    ${usernameError}
                                                </div>
                                            </#if>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-2 col-sm-3 col-xs-12 control-label">Password</label>
                                        <div class="col-md-10 col-sm-9 col-xs-12">
                                            <input type="password" name="password"
                                                   class="form-control ${(passwordError??)?string('is-invalid', '')}"/>
                                            <#if passwordError??>
                                                <div class="invalid-feedback">
                                                    ${passwordError}
                                                </div>
                                            </#if>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-2 col-sm-3 col-xs-12 control-label">Retype password</label>
                                        <div class="col-md-10 col-sm-9 col-xs-12">
                                            <input type="password" name="password2"
                                                   class="form-control ${(password2Error??)?string('is-invalid', '')}"/>
                                            <#if password2Error??>
                                                <div class="invalid-feedback">
                                                    ${password2Error}
                                                </div>
                                            </#if>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-2 col-sm-3 col-xs-12 control-label">First Name</label>
                                        <div class="col-md-10 col-sm-9 col-xs-12">
                                            <input type="text" class="form-control" value="Rebecca">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-2 col-sm-3 col-xs-12 control-label">Last Name</label>
                                        <div class="col-md-10 col-sm-9 col-xs-12">
                                            <input type="text" class="form-control" value="Sanders">
                                        </div>
                                    </div>
                                </fieldset>
                                <fieldset class="fieldset">
                                    <h3 class="fieldset-title">Contact Info</h3>
                                    <div class="form-group">
                                        <label class="col-md-2  col-sm-3 col-xs-12 control-label">Email</label>
                                        <div class="col-md-10 col-sm-9 col-xs-12">
                                            <input type="email" name="email" class="form-control ${(emailError??)?string('is-invalid', '')}"
                                                   value="<#if user??>${user.email}</#if>">
                                            <p class="help-block">This is the email </p>
                                            <#if emailError??>
                                                <div class="invalid-feedback">
                                                    ${emailError}
                                                </div>
                                            </#if>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-2  col-sm-3 col-xs-12 control-label">Twitter</label>
                                        <div class="col-md-10 col-sm-9 col-xs-12">
                                            <input type="text" class="form-control" value="SpeedyBecky">
                                            <p class="help-block">Your twitter username</p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-2  col-sm-3 col-xs-12 control-label">Linkedin</label>
                                        <div class="col-md-10 col-sm-9 col-xs-12">
                                            <input type="url" class="form-control" value="https://www.linkedin.com/in/lorem">
                                            <p class="help-block">eg. https://www.linkedin.com/in/yourname</p>
                                        </div>
                                    </div>
                                </fieldset>
                                <hr>
                                <div class="form-group">
                                    <div class="col-md-10 col-sm-9 col-xs-12 col-md-push-2 col-sm-push-3 col-xs-push-0">
                                        <input class="btn btn-primary" type="submit" value="Update Profile">
                                    </div>
                                </div>
                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            </form>
                        </div>
                    </div>
                </section>
            </div>
        </div>
    </form>
</@c.page>
