<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../comunes/inicioHTML.jsp">
    <jsp:param name="elTitulo" value="Detalle del alumno" />
</jsp:include>

<jsp:include page="../comunes/navbar.jsp" />

<section class="container">
    <div class="row py-3 align-items-center justify-content-center">
        <div class="col-12" >
            <h1>Detalles del alumno</h1>

            <section class="d-flex gap-5">
                <img class="w-25 h-25" src="${alumnoPreview.foto}" alt="Foto de ${alumnoPreview.nombre}" />    

                <section>
                    <p>ID del alumno: ${alumnoPreview.id}</p>
                    <p>Nombre y Apellido del Alumno: ${alumnoPreview.nombre} ${alumnoPreview.apellido}</p>
                    <p>Fecha de nacimiento del alumno: ${alumnoPreview.fechaNacimiento}</p>
                    <p>Edad del alumno: ${2022 - Integer.parseInt(alumnoPreview.fechaNacimiento.split("-")[0]) }</p>
                    <p>Mail: ${alumnoPreview.mail}</p>

                    <div class="col-4">
                        <a href="${pageContext.request.contextPath}/app?accion=edit&id=${alumnoPreview.id}" class="btn bg-warning w-100"><i class="bi bi-pencil"></i></a>
                    </div>
                    <div class="col-4">
                        <a href="${pageContext.request.contextPath}/app?accion=remove&id=${alumnoPreview.id}" class="btn bg-danger text-light w-100"><i class="bi bi-trash3"></i></a>
                    </div>
                </section>
            </section>
            <a class="btn btn-warning" href="${pageContext.request.contextPath}/app">Volver</a>
        </div>
    </div>
</section>


<jsp:include page="../comunes/footer.jsp"/>
<jsp:include page="../comunes/finHTML.jsp"/>