package M7_Activity2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final List<M7Exercise1> entityList = new ArrayList<>();
    private static final List<String> apiCalls = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        port(4568);

        get("/check-connection", (req, res) -> {
            res.type("application/json");
            Map<String, String> response = new HashMap<>();
            response.put("status", "Server is running");
            return JsonUtil.toJson(response);
        });

        get("/hello-world", (req, res) -> {
            res.type("application/json");
            String name = req.queryParams("name");
            if (name == null) name = "World";
            Map<String, String> response = new HashMap<>();
            response.put("message", "Hello " + name);
            return JsonUtil.toJson(response);
        });

        post("/echo", (req, res) -> {
            res.type("application/json");
            return req.body();
        });

        M7Exercise1 entry1 = new M7Exercise1(1L, "Jose Rizal", "Illustrados", null);
        M7Exercise1 entry2 = new M7Exercise1(2L, "Andres Bonifacio", "KKK", null);
        M7Exercise1 entry3 = new M7Exercise1(3L, "Emilio Aguinaldo", "Magdalo", null);
        entityList.add(entry1);
        entityList.add(entry2);
        entityList.add(entry3);

        get("/profiles", (req, res) -> {
            Map<String, Object> response = new HashMap<>();
            apiCalls.add("GET: /profiles");
            res.type("application/json");
            response.put("status", "Success");
            response.put("data", entityList);
            return JsonUtil.toJson(response);
        });

        post("/profiles", (req, res) -> {
            Map<String, Object> response = new HashMap<>();
            apiCalls.add("POST: /profiles");
            res.type("application/json");
            if (req.body() == null || req.body().isBlank()) {
                res.status(400);
                response.put("status", "ERROR");
                response.put("data", null);
                response.put("message", "Request body cannot be null");
                return JsonUtil.toJson(response);
            }
            M7Exercise1 data = JsonUtil.fromJson(req.body(), M7Exercise1.class);
            data.setApiCallsMade(apiCalls);
            entityList.add(data);
            response.put("status", "Success");
            response.put("data", entityList);
            return JsonUtil.toJson(response);
        });

        put("/profiles", (req, res) -> {
            Map<String, Object> response = new HashMap<>();
            apiCalls.add("PUT: /profiles");
            res.type("application/json");
            String name = req.queryParams("name");
            if (name == null || name.isBlank()) {
                response.put("status", "ERROR");
                response.put("data", null);
                response.put("message", "Name query param is required!");
                return JsonUtil.toJson(response);
            }
            if (req.body() == null || req.body().isBlank()) {
                response.put("status", "ERROR");
                response.put("data", null);
                response.put("message", "Request body cannot be null!");
                return JsonUtil.toJson(response);
            }
            M7Exercise1 data = JsonUtil.fromJson(req.body(), M7Exercise1.class);
            M7Exercise1 searchEntity = entityList.stream()
                    .filter(x -> x.getName() != null && x.getName().toLowerCase().contains(name.toLowerCase()))
                    .findFirst().orElse(null);
            if (searchEntity == null) {
                response.put("status", "ERROR");
                response.put("data", null);
                response.put("message", "Could not find profile with name containing: " + name);
                return JsonUtil.toJson(response);
            }
            searchEntity.setName(data.getName());
            searchEntity.setGroup(data.getGroup());
            response.put("status", "Success");
            response.put("data", entityList);
            return JsonUtil.toJson(response);
        });

        delete("/profiles/:id", (req, res) -> {
            Map<String, Object> response = new HashMap<>();
            apiCalls.add("DELETE: /profiles");
            res.type("application/json");
            String paramId = req.params("id");
            if (paramId == null || paramId.isBlank()) {
                response.put("status", "ERROR");
                response.put("data", null);
                response.put("message", "ID is required!");
                return JsonUtil.toJson(response);
            }
            Long id = Long.valueOf(paramId);
            M7Exercise1 searchEntity = entityList.stream()
                    .filter(x -> x.getId() != null && x.getId().equals(id))
                    .findFirst().orElse(null);
            if (searchEntity == null) {
                response.put("status", "ERROR");
                response.put("data", null);
                response.put("message", "Could not find profile with ID = " + paramId);
                return JsonUtil.toJson(response);
            }
            entityList.remove(searchEntity);
            response.put("status", "Success");
            response.put("data", entityList);
            return JsonUtil.toJson(response);
        });

        logger.info("Server started at port {}", port());
    }
}