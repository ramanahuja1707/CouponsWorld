package com.couponsworld.services;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.couponsworld.dto.Category;
import com.couponsworld.dto.Offer;
import com.couponsworld.exceptions.CategoryException;
import com.couponsworld.exceptions.OfferException;
import com.googlecode.objectify.ObjectifyService;

public class DatabaseService {
	static {
		// Registeration of the entities (dto's)
		ObjectifyService.register(Offer.class);
		ObjectifyService.register(Category.class);
	}

	public static long offerNo = 0L;
	public static long categoryNo = 0L;

	public static Offer createOfferInDatabase(Offer offer) throws Exception {
		try {

			offer.setOfferId(++offerNo);
			ofy().save().entity(offer);
			return offer;
		} catch (Exception exception) {
			throw exception;
		}
	}

	public static Offer updateOfferInDatabase(long offerId, Offer offer) throws OfferException, Exception {
		try {
			Offer offerRetreivedFromDatabase = ofy().load().type(Offer.class).id(offerId).now();
			if (offerRetreivedFromDatabase != null) {
				offer.setOfferId(offerId);
				ofy().delete().entities(offerRetreivedFromDatabase).now();
				ofy().save().entity(offer).now();
				return offer;
			} else {
				throw new OfferException("Offer not existing with offerId : " + offerId);
			}
		} catch (Exception exception) {
			throw exception;
		}
	}

	public static List<Offer> getOffersFromDatabase() throws OfferException, Exception {
		try {
			List<Offer> offersRetreivedFromDatabase = ofy().load().type(Offer.class).list();
			// if (offersRetreivedFromDatabase.size() != 0) {

			return offersRetreivedFromDatabase;
			// }
		} catch (Exception exception) {
			throw exception;
		}
	}

	public static Offer deleteOfferFromDatabase(long offerId) throws OfferException, Exception {
		try {
			Offer offerRetreivedFromDatabase = ofy().load().type(Offer.class).id(offerId).now();
			if (offerRetreivedFromDatabase != null) {
				ofy().delete().entities(offerRetreivedFromDatabase).now();
				return offerRetreivedFromDatabase;
			} else {
				throw new OfferException("Offer not existing with offerId : " + offerId);
			}
		} catch (Exception exception) {
			throw exception;
		}
	}

	public static Category createCategoryInDatabase(Category category) throws Exception {
		try {
			category.setCategoryId(++categoryNo);
			ofy().save().entity(category);
			return category;
		} catch (Exception exception) {
			throw exception;
		}
	}

	public static List<Category> getCategoriesFromDatabase() throws CategoryException, Exception {
		try {
			List<Category> categoriesRetreivedFromDatabase = ofy().load().type(Category.class).list();
			// if (offersRetreivedFromDatabase.size() != 0) {

			return categoriesRetreivedFromDatabase;
			// }
		} catch (Exception exception) {
			throw exception;
		}
	}

	public static Category updateCategoryInDatabase(long categoryId, Category category)
			throws CategoryException, Exception {
		try {
			Category categoryRetreivedFromDatabase = ofy().load().type(Category.class).id(categoryId).now();
			if (categoryRetreivedFromDatabase != null) {
				category.setCategoryId(categoryId);
				ofy().delete().entities(categoryRetreivedFromDatabase).now();
				ofy().save().entity(category).now();
				return category;
			} else {
				throw new CategoryException("Category not existing with category Id : " + categoryId);
			}
		} catch (Exception exception) {
			throw exception;
		}
	}

	public static Category deleteCategoryFromDatabase(long categoryId) throws CategoryException, Exception {
		try {
			Category categoryRetreivedFromDatabase = ofy().load().type(Category.class).id(categoryId).now();
			if (categoryRetreivedFromDatabase != null) {
				ofy().delete().entities(categoryRetreivedFromDatabase).now();
				return categoryRetreivedFromDatabase;
			} else {
				throw new CategoryException("Category not existing with category Id : " + categoryId);
			}
		} catch (Exception exception) {
			throw exception;
		}
	}

}
