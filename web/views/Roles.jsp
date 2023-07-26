<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.pagos.model.*"%>
<%@page import="com.pagos.dao.*"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Dashboard</title>
        <meta content="" name="description">
        <meta content="" name="keywords">

        <!-- Favicons -->
        <link href="assets/img/favicon.png" rel="icon">
        <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

        <!-- Google Fonts -->
        <link href="https://fonts.gstatic.com" rel="preconnect">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

        <!-- Vendor CSS Files -->
        <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
        <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
        <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">

        <!-- Template Main CSS File -->
        <link href="assets/css/style.css" rel="stylesheet">
    </head>
    <body>
        <header id="header" class="header fixed-top d-flex align-items-center">
            <div class="d-flex align-items-center justify-content-between">
                <span class="logo d-flex align-items-center text-uppercase">
                    <span class="d-none d-lg-block">ROL PAGOS</span>
                </span>
                <i class="bi bi-list toggle-sidebar-btn"></i>
            </div>           
            <nav class="header-nav ms-auto">
                <ul class="d-flex align-items-center">
                    <li class="nav-item d-block d-lg-none">
                        <a class="nav-link nav-icon search-bar-toggle " href="#">
                            <i class="bi bi-search"></i>
                        </a>
                    </li>
                    <li class="nav-item dropdown pe-3">
                        <a class="nav-link nav-profile d-flex align-items-center pe-0" href="#" data-bs-toggle="dropdown">                          
                            <span class="d-none d-md-block dropdown-toggle ps-2 text-uppercase">${usuUsuario}</span>
                        </a><!-- End Profile Iamge Icon -->
                        <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">                            
                            <li>
                                <a class="dropdown-item d-flex align-items-center" href="CerrarSesion">
                                    <i class="bi bi-box-arrow-right"></i>
                                    <span>Cerrar sesion</span>
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </nav>
        </header>
        <aside id="sidebar" class="sidebar">
            <ul class="sidebar-nav" id="sidebar-nav">
                <li class="nav-item">
                    <a class="nav-link " href="Controlador?p=rol">
                        <i class="bi bi-grid"></i>
                        <span>Roles</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link " href="Controlador?p=empleados">
                        <i class="bi bi-grid"></i>
                        <span>Usuarios</span>
                    </a>
                </li>                
            </ul>

        </aside>

        <main id="main" class="main">
            <div class="col-lg-12">
                <!--<a href="Usuarios?accion=nuevo" class="btn btn-primary rounded-pill mb-3">Ver lista de Roles</a>-->
                <div class="card">
                    <div class="card-header">
                        <h4>NUEVO ROL DE PAGO</h4>
                    </div>
                    <div class="card-body p-4">
                        <div class="row">
                            <div class="col-xl-6">
                                <p class="mb-1 text-primary">Empleado:</p>
                                <div class="input-group mb-3">
                                    <input type="text" name="nombres" id="nombres" class="form-control" id="lblNombreProd">
                                    <button class="btn btn-primary" type="button" data-bs-toggle="modal" data-bs-target="#modalUsuarios">Buscar</button>
                                </div>
                                <input class="form-control" type="hidden" required="true" name="idUsuario" value="${idUsuario}">
                                <input class="form-control" type="hidden" required="true" name="fkUsuario">
                            </div>
                            <div class="col-xl-6 mb-3">
                                <div class="example">
                                    <p class="mb-1 text-primary">Fecha emision</p>
                                    <input type="date" class="form-control" required="true" name="encEmision" id="encEmision">
                                </div>
                            </div>            
                            <div class="col-xl-6 mb-3">
                                <div class="example">
                                    <p class="mb-1 text-primary">Fecha inicio:</p>
                                    <input type="date" class="form-control" required="true" name="detInicio" id="detInicio">
                                </div>
                            </div>
                            <div class="col-xl-6 mb-3">
                                <div class="example">
                                    <p class="mb-1 text-primary">Fecha fin</p>
                                    <input type="date" class="form-control" required="true" name="detFin" id="detFin">
                                </div>
                            </div>               
                            <div class="col-xl-6 mb-3">
                                <div class="example">
                                    <p class="mb-1 text-primary">Horas trabajadas:</p>
                                    <input type="number" class="form-control" required="true" name="detHoras" id="detHoras">
                                </div>
                            </div>
                            <div class="col-xl-6 mb-3">
                                <div class="example">
                                    <p class="mb-1 text-primary">Valor por hora</p>
                                    <input type="number" class="form-control" required="true" name="detHoraValor" id="detHoraValor">
                                </div>
                            </div>     
                        </div>   
                        <button type="button" class="btn btn-primary mb-3" onclick="calcularRolDePagos()">Calcular</button>
                        <div class="table-responsive">
                            <table class="table text-center h-100 py-5" id="detalle-table">
                                <thead>
                                    <tr>
                                        <th>Fecha inicio</th>
                                        <th>Fecha fin</th>
                                        <th>Horas trabajadas</th>
                                        <th>Valor por hora</th>
                                        <th>Total</th>
                                    </tr>
                                </thead>
                                <tbody id="detalle-table-body">
                                    <!-- Existing table rows will be appended here -->
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class=" card-footer">
                        <div class="d-flex">
                            <div class="mr-2 bold">TOTAL A PAGAR:</div>
                            <div class="mx-5 text-black" id="totalPagar">0.00</div>
                        </div>
                        <br>
                        <button class="btn btn-primary mb-3" onclick="calcularRolDePagos()">Calcular</button>
                    </div>

                </div>
            </div
        </main>

        <div class="modal fade" id="modalUsuarios">
            <div class="modal-dialog modal-dialog-centered modal-xl" role="document">
                <div class="modal-content modal-sm">
                    <div class="modal-body">
                        <center>
                            <h3 class="modal-title text-primary"><b>EMPLEADOS</b></h3>
                        </center>
                        <div class="table-responsive">
                            <table id="example" class="display table header-border verticle-middle" style="min-width: 845px" onclick="selectClient(this)">
                                <thead>
                                    <tr>                                           
                                        <th class="text-center">ID</th>                                            
                                        <th class="text-center">Nombres</th>                                                  
                                        <th class="text-center">Apellidos</th>                                                  
                                        <th class="text-center">Telefono</th>                                                  
                                        <th class="text-center">Direccion</th>                                                  
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                                    UsuarioDao daoCh = new UsuarioDao();
                                                    List<Usuario> listCh = daoCh.getAll();
                                                    Iterator<Usuario> iterCh = listCh.iterator();
                                                    Usuario ch = null;
                                                    while (iterCh.hasNext()) {
                                                        ch = iterCh.next();
                                    %>
                                    <tr>                                                    
                                        <td class="text-center text-muted"><%= ch.getIdUsuario()%></td>                                            
                                        <td class="text-center text-muted"><%= ch.getUsuNombres()%></td>                                            
                                        <td class="text-center text-muted"><%= ch.getUsuApellidos()%></td>                                            
                                        <td class="text-center text-muted"><%= ch.getUsuTelefono()%></td>                                            
                                        <td class="text-center text-muted"><%= ch.getUsuDireccion()%></td>                                            
                                    </tr>  
                                    <%}%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

        <!-- Vendor JS Files -->
        <script src="assets/vendor/apexcharts/apexcharts.min.js"></script>
        <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Template Main JS File -->
        <script src="assets/js/main.js"></script>
        <script src="assets/js/Calculos.js"></script>
        <script>


        </script>

    </body>

</html>