<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="utf-8" />
            <meta http-equiv="X-UA-Compatible" content="IE=edge" />
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
            <meta name="description" content="Hỏi Dân IT - Dự án laptopshop" />
            <meta name="author" content="Hỏi Dân IT" />
            <title>Dashboard - Hỏi Dân IT</title>
            <link href="/css/styles.css" rel="stylesheet" />
            <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
            <script>
                $(document).ready(() => {
                    const avatarFile = $("#avatarFile");
                    avatarFile.change(function (e) {
                        const imgURL = URL.createObjectURL(e.target.files[0]);
                        $("#avatarPreview").attr("src", imgURL);
                        $("#avatarPreview").css({ "display": "block" });
                    });
                });
            </script>
        </head>

        <body class="sb-nav-fixed">
            <jsp:include page="../layout/header.jsp" />
            <div id="layoutSidenav">
                <jsp:include page="../layout/sidebar.jsp" />
                <div id="layoutSidenav_content">
                    <main>
                        <div class="container-fluid px-4">
                            <h1 class="mt-4">Product Detail</h1>
                            <ol class="breadcrumb mb-4">
                                <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                                <li class="breadcrumb-item active">Detail</li>
                            </ol>
                            <div class="mt-5">
                                <div class="row">
                                    <div class="col-12 mx-auto">
                                        <div class="d-flex justify-content-between">
                                            <h3>Product detail with id = ${id}</h3>
                                            <a href="/admin/product/create" class="btn btn-primary">Create a Product</a>
                                        </div>

                                        <hr />
                                        <div class="card" style="width: 70%;">

                                            <img style="width: 60%; height: 60%;" class="card-img-top"
                                                src="/images/product/${product.image}" alt="">



                                            <div class="card-header">
                                                Product information
                                            </div>
                                            <ul class="list-group list-group-flush">
                                                <li class="list-group-item">ID: ${product.id}</li>
                                                <li class="list-group-item">Name: ${product.name}</li>
                                                <li class="list-group-item">Detail description: ${product.detailDesc}
                                                </li>
                                                <li class="list-group-item">Short description: ${product.shortDesc}</li>
                                                <li class="list-group-item">Price: ${product.price}</li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="container mt-3">
                                <a href="/admin/product" class="btn btn-success">Back</a>
                            </div>
                        </div>
                    </main>
                    <jsp:include page="../layout/footer.jsp" />
                </div>
            </div>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                crossorigin="anonymous"></script>
            <script src="js/scripts.js"></script>

        </body>

        </html>