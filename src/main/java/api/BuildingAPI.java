package api;

import builder.BuildingSearchBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.BuildingDTO;
import paging.PageRequest;
import paging.Pageable;
import service.IBuildingService;
import service.impl.BuildingService;
import utils.HttpUtil;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BuildingAPI extends HttpServlet {
    private IBuildingService buildingService = new BuildingService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        BuildingDTO building = HttpUtil.of(request.getReader()).toModel(BuildingDTO.class);
        BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
                .setName(building.getName())
                .setDistrict(building.getDistrict())
                .setBuildingArea(building.getBuildingArea())
                .setNumberOfBasement(building.getNumberOfBasement())
                .build();
        Pageable pageable = new PageRequest(building.getPage(), building.getSize());
        List<BuildingDTO> buildings = buildingService.findAll(buildingSearchBuilder, pageable);
        mapper.writeValue(response.getOutputStream(), buildings);
    }
}
