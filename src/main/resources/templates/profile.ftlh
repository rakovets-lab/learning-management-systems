<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">

<@c.page>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="/static/css/profile.css" rel="stylesheet">
    <form method="post" action="/user/profile" enctype="multipart/form-data" xmlns="http://www.w3.org/1999/html">
        <div class="container">
            <div class="view-account">
                <section class="module">
                    <div class="module-inner">
                        <div class="side-bar">
                            <div class="user-info">
                                <img class="img-profile img-circle img-responsive center-block"
                                     src="https://bootdey.com/img/Content/avatar/avatar1.png" alt="">
                                <ul class="meta list list-unstyled">
                                    <li class="name"><#if user??>${user.username}</#if></li>
                                    <li class="email">${email!''}</li>
                                </ul>
                            </div>
                            <nav class="side-menu">
                                <ul class="nav">
                                    <li class="active"><a href="/user/profile"><span class="fa fa-user"></span> Profile</a></li>
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
                                            <#if avatar??>
                                                <img class="img-rounded img-responsive" src="/avatar/${avatar}"
                                                     alt="">
                                            </#if>
                                        </figure>

                                        <div class="form-inline col-md-10 col-sm-9 col-xs-12">
                                            <div class="form-group row">
                                                <div class="col-md-8">
                                                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                                    <label class="col-md-4 col-form-label" for="file">Avatar
                                                        image:</label>
                                                    <input type="file" name="file" id="file"
                                                           class="form-control-file">
                                                </div>
                                            </div>
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
                                </fieldset>
                                <fieldset class="fieldset">
                                    <h3 class="fieldset-title">Contact Info</h3>
                                    <div class="form-group">
                                        <label class="col-md-2  col-sm-3 col-xs-12 control-label">Email</label>
                                        <div class="col-md-10 col-sm-9 col-xs-12">
                                            <input type="email" name="email"
                                                   class="form-control ${(emailError??)?string('is-invalid', '')}"
                                                   value="<#if user??>${user.email!''}</#if>"
                                                   placeholder="example@example.com">
                                            <#if emailError??>
                                                <div class="invalid-feedback">
                                                    ${emailError}
                                                </div>
                                            </#if>
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
