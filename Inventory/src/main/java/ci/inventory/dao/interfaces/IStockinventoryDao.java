package ci.inventory.dao.interfaces;

import java.util.List;

import ci.inventory.entity.Stockinventory;

public interface IStockinventoryDao {
	//Definition Basics operations
	Stockinventory create(Stockinventory stockinventory);
	Stockinventory getById(int id);
	Stockinventory update(Stockinventory stockinventory);
	int delete(int id);
	List<Stockinventory> getAll();
	Stockinventory getByIdProduct(int idproduct);
	Stockinventory stockupdate(int idproduit, int quantity, int iduser) throws Exception;
}
