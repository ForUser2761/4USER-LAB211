package data_layer;

import business_layer.entity.Hotel;
import data_layer.hotel_dao.IHotelDao;
public interface IDaoFactory {
    IHotelDao<Hotel> hotelDao() throws Exception;
}
