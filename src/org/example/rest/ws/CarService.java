package org.example.rest.ws;

import org.example.dao.impl.CarsDAO;
import org.example.model.Car;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;


@Path("/cars") //Path определяет класс как ресурс и задает путь к нему.

public class CarService {

    private CarsDAO dao = CarsDAO.getInstance(); // ссылка на мнимую базу данных


    @GET  //GET задает метод, вызываемый при поступлении GET-запроса.  является обязательной.
    @Path("/is-alive")
    @Produces(MediaType.TEXT_PLAIN)
    //Produces задает тип данных отправляемых клиенту в теле отклике и не является обязательной.

    public String isAlive() {
        return "Server time is " + new Date();
        //проверка (при запуске wep-app/rest/cars/is-alive - будет: Server time is Fri Feb 03 12:14:18 MSK 2023 - текущее время)
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response showAll() {
        List<Car> all = dao.all();
        if (all.size() > 0) {
            return Response.ok(all).build();
        }
        return Response.noContent().build();
    }

    @GET
    @Path("/all-xml")
    @Produces(MediaType.APPLICATION_XML)
    public Response showAllInXml() {
        List<Car> all = dao.all();
        if (all.size() > 0) {
            GenericEntity<List<Car>> genericEntity = new GenericEntity<List<Car>>(all) {
            };

            return Response.ok(genericEntity).build();
        }
        return Response.noContent().build();
    }

    //GenericEntity - Представляет объект ответа универсального типа T.
    //позволяет извлекать информацию о типе во время выполнения, несмотря на стирание типа

    //будем проверять этим методом, как он работает с нашей мнимой базоый данных
    @GET
    @Path("/first") //путь: wep-app/rest/cars/first
    @Produces(MediaType.APPLICATION_JSON)
    //Produces задает тип данных отправляемых клиенту в теле отклик. Будем работь с JSON
    public Response first() { //first - хотим получить данные первой машины BMW M3
        List<Car> all = dao.all();
        //проверка: если список пустой  - isEmpty, noContent - без содержания
        if (all.isEmpty()) {
            return Response.noContent().build();
        }
        return Response.ok(all.get(0)).build();
    }

    //Метод GET, который работает по какому-то параметру, например, по id
    @GET
    @Path("{id}") //путь: wep-app/rest/cars/first
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById (@PathParam("id") int id) {
        Car car = dao.getById(id);
        //проверка: если список пустой  - isEmpty, noCont(@PathParam("id") int id) ent - без содержания
        if (car != null) {
            return Response.ok(car).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
        //NOT_FOUND - 404 Not Found согласно документацию по HTTP
    }

    //
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON) //Consumes - потреблять
    public Response addCar(Car car) {
        int id = dao.add(car);
        return Response.ok(car).build();
    }
    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON) //Consumes - потреблять
    public Response update(Car car) {
        boolean isUpdate = dao.update(car);
        //проверка
        if (isUpdate) { // если обновилось, дать ответ UPDATED (обновлено)
            return Response.ok("UPDATED").build();
        }
        return Response.status(Response.Status.NOT_MODIFIED).build(); //Если не обновилось статус - 304 Not Modified
        // 304 NOT_MODIFIED - не обноилось согласно документацию по HTTP
    }

    @DELETE
    @Path("/delete/{id}")
    public Response isDelete(@PathParam("id") int id)  {
        boolean isDeleted = dao.delete(id);
        //проверка
        if (isDeleted) { // если удалено, дать ответ DELETE
            return Response.ok("DELETE").build();
        }
        return Response.status(Response.Status.NOT_MODIFIED).build(); //Если не обновилось статус - 304 Not Modified
        // 304 NOT_MODIFIED - не обноилось согласно документацию по HTTP
    }


}