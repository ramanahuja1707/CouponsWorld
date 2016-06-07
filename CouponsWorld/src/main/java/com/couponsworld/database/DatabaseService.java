package com.couponsworld.database;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.couponsworld.dto.Category;
import com.couponsworld.dto.Company;
import com.couponsworld.dto.Offer;
import com.couponsworld.dto.SubCategory;
import com.couponsworld.dto.UsabilityStatus;
import com.couponsworld.dto.UserPlatform;
import com.couponsworld.dto.UserType;
import com.couponsworld.exceptions.CategoryException;
import com.couponsworld.exceptions.CompanyException;
import com.couponsworld.exceptions.OfferException;
import com.couponsworld.exceptions.SubCategoryException;
import com.couponsworld.exceptions.UsabilityStatusException;
import com.couponsworld.exceptions.UserPlatformException;
import com.couponsworld.exceptions.UserTypeException;
import com.googlecode.objectify.ObjectifyService;

public class DatabaseService {
	static {
		// Registeration of the entities (dto's)
		ObjectifyService.register(Offer.class);
		ObjectifyService.register(Category.class);
		ObjectifyService.register(Company.class);
		ObjectifyService.register(SubCategory.class);
		ObjectifyService.register(UsabilityStatus.class);
		ObjectifyService.register(UserPlatform.class);
		ObjectifyService.register(UserType.class);
	}

	private static long offerNo = 0L;
	private static long categoryNo = 0L;
	private static long companyNo = 0L;
	private static long subCategoryNo = 0L;
	private static long usabilityStatusNo = 0L;
	private static long userPlatformNo = 0L;
	private static long userTypeNo = 0L;

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

	// --------------------------------------------------------------------------------------------

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

	// --------------------------------------------------------------------------------------------

	public static Company createCompanyInDatabase(Company company) throws Exception {
		try {
			company.setCompanyId(++companyNo);
			ofy().save().entity(company);
			return company;
		} catch (Exception exception) {
			throw exception;
		}
	}

	public static List<Company> getCompaniesFromDatabase() throws CompanyException, Exception {
		try {
			List<Company> companiesRetreivedFromDatabase = ofy().load().type(Company.class).list();
			// if (offersRetreivedFromDatabase.size() != 0) {

			return companiesRetreivedFromDatabase;
			// }
		} catch (Exception exception) {
			throw exception;
		}
	}

	public static Company updateCompanyInDatabase(long companyId, Company company) throws CompanyException, Exception {
		try {
			Company companyRetreivedFromDatabase = ofy().load().type(Company.class).id(companyId).now();
			if (companyRetreivedFromDatabase != null) {
				company.setCompanyId(companyId);
				ofy().delete().entities(companyRetreivedFromDatabase).now();
				ofy().save().entity(company).now();
				return company;
			} else {
				throw new CompanyException("Company not existing with company Id : " + companyId);
			}
		} catch (Exception exception) {
			throw exception;
		}
	}

	public static Company deleteCompanyFromDatabase(long companyId) throws CompanyException, Exception {
		try {
			Company companyRetreivedFromDatabase = ofy().load().type(Company.class).id(companyId).now();
			if (companyRetreivedFromDatabase != null) {
				ofy().delete().entities(companyRetreivedFromDatabase).now();
				return companyRetreivedFromDatabase;
			} else {
				throw new CompanyException("Company not existing with company Id : " + companyId);
			}
		} catch (Exception exception) {
			throw exception;
		}
	}

	// --------------------------------------------------------------------------------------------

	public static SubCategory createSubCategoryInDatabase(SubCategory subCategory) throws Exception {
		try {
			subCategory.setSubCategoryId(++subCategoryNo);
			ofy().save().entity(subCategory);
			return subCategory;
		} catch (Exception exception) {
			throw exception;
		}
	}

	public static List<SubCategory> getSubCategoriesFromDatabase() throws SubCategoryException, Exception {
		try {
			List<SubCategory> subCategoriesRetreivedFromDatabase = ofy().load().type(SubCategory.class).list();
			// if (offersRetreivedFromDatabase.size() != 0) {

			return subCategoriesRetreivedFromDatabase;
			// }
		} catch (Exception exception) {
			throw exception;
		}
	}

	public static SubCategory updateSubCategoryInDatabase(long subCategoryId, SubCategory subCategory)
			throws SubCategoryException, Exception {
		try {
			SubCategory subCategoryRetreivedFromDatabase = ofy().load().type(SubCategory.class).id(subCategoryId).now();
			if (subCategoryRetreivedFromDatabase != null) {
				subCategory.setSubCategoryId(subCategoryId);
				ofy().delete().entities(subCategoryRetreivedFromDatabase).now();
				ofy().save().entity(subCategory).now();
				return subCategory;
			} else {
				throw new SubCategoryException("Sub Category not existing with subcategoryId : " + subCategoryId);
			}
		} catch (Exception exception) {
			throw exception;
		}
	}

	public static SubCategory deleteSubCategoryFromDatabase(long subCategoryId) throws SubCategoryException, Exception {
		try {
			SubCategory subCategoryRetreivedFromDatabase = ofy().load().type(SubCategory.class).id(subCategoryId).now();
			if (subCategoryRetreivedFromDatabase != null) {
				ofy().delete().entities(subCategoryRetreivedFromDatabase).now();
				return subCategoryRetreivedFromDatabase;
			} else {
				throw new SubCategoryException("Sub Category not existing with subCategoryId : " + subCategoryId);
			}
		} catch (Exception exception) {
			throw exception;
		}
	}

	// ---------------------------------------------------------------------------------------------

	public static UsabilityStatus createUsabilityStatusInDatabase(UsabilityStatus usabilityStatus) throws Exception {
		try {
			usabilityStatus.setUsabilityStatusId(++usabilityStatusNo);
			ofy().save().entity(usabilityStatus);
			return usabilityStatus;
		} catch (Exception exception) {
			throw exception;
		}
	}

	public static List<UsabilityStatus> getUsabilityStatusFromDatabase() throws UsabilityStatusException, Exception {
		try {
			List<UsabilityStatus> usabilityStatusRetreivedFromDatabase = ofy().load().type(UsabilityStatus.class)
					.list();
			// if (offersRetreivedFromDatabase.size() != 0) {

			return usabilityStatusRetreivedFromDatabase;
			// }
		} catch (Exception exception) {
			throw exception;
		}
	}

	public static UsabilityStatus updateUsabilityStatusInDatabase(long usabilityStatusId,
			UsabilityStatus usabilityStatus) throws UsabilityStatusException, Exception {
		try {
			UsabilityStatus usabilityStatusRetreivedFromDatabase = ofy().load().type(UsabilityStatus.class)
					.id(usabilityStatusId).now();
			if (usabilityStatusRetreivedFromDatabase != null) {
				usabilityStatus.setUsabilityStatusId(usabilityStatusId);
				ofy().delete().entities(usabilityStatusRetreivedFromDatabase).now();
				ofy().save().entity(usabilityStatus).now();
				return usabilityStatus;
			} else {
				throw new UsabilityStatusException("Usability Status not existing with id : " + usabilityStatusId);
			}
		} catch (Exception exception) {
			throw exception;
		}
	}

	public static UsabilityStatus deleteUsabilityStatusFromDatabase(long usabilityStatusId)
			throws UsabilityStatusException, Exception {
		try {
			UsabilityStatus usabilityStatusRetreivedFromDatabase = ofy().load().type(UsabilityStatus.class)
					.id(usabilityStatusId).now();
			if (usabilityStatusRetreivedFromDatabase != null) {
				ofy().delete().entities(usabilityStatusRetreivedFromDatabase).now();
				return usabilityStatusRetreivedFromDatabase;
			} else {
				throw new UsabilityStatusException("UsabilityStatus not existing with id : " + usabilityStatusId);
			}
		} catch (Exception exception) {
			throw exception;
		}
	}

	// ---------------------------------------------------------------------------------------------

	public static UserPlatform createUserPlatformInDatabase(UserPlatform userPlatform) throws Exception {
		try {
			userPlatform.setUserPlatformId(++userPlatformNo);
			ofy().save().entity(userPlatform);
			return userPlatform;
		} catch (Exception exception) {
			throw exception;
		}
	}

	public static List<UserPlatform> getUserPlatformFromDatabase() throws UserPlatformException, Exception {
		try {
			List<UserPlatform> userPlatformRetreivedFromDatabase = ofy().load().type(UserPlatform.class).list();
			// if (offersRetreivedFromDatabase.size() != 0) {

			return userPlatformRetreivedFromDatabase;
			// }
		} catch (Exception exception) {
			throw exception;
		}
	}

	public static UserPlatform updateUserPlatformInDatabase(long userPlatformId, UserPlatform userPlatform)
			throws UserPlatformException, Exception {
		try {
			UserPlatform userPlatformRetreivedFromDatabase = ofy().load().type(UserPlatform.class).id(userPlatformId)
					.now();
			if (userPlatformRetreivedFromDatabase != null) {
				userPlatform.setUserPlatformId(userPlatformId);
				ofy().delete().entities(userPlatformRetreivedFromDatabase).now();
				ofy().save().entity(userPlatform).now();
				return userPlatform;
			} else {
				throw new UserPlatformException("User Platform Status not existing with id : " + userPlatformId);
			}
		} catch (Exception exception) {
			throw exception;
		}
	}

	public static UserPlatform deleteUserPlatformFromDatabase(long userPlatformId)
			throws UserPlatformException, Exception {
		try {
			UserPlatform userPlatformRetreivedFromDatabase = ofy().load().type(UserPlatform.class).id(userPlatformId)
					.now();
			if (userPlatformRetreivedFromDatabase != null) {
				ofy().delete().entities(userPlatformRetreivedFromDatabase).now();
				return userPlatformRetreivedFromDatabase;
			} else {
				throw new UserPlatformException("UserPlatform not existing with id : " + userPlatformId);
			}
		} catch (Exception exception) {
			throw exception;
		}
	}

	// ---------------------------------------------------------------------------------------------

	public static UserType createUserTypeInDatabase(UserType userType) throws Exception {
		try {
			userType.setUserTypeId(++userTypeNo);
			ofy().save().entity(userType);
			return userType;
		} catch (Exception exception) {
			throw exception;
		}
	}

	public static List<UserType> getUserTypeFromDatabase() throws UserTypeException, Exception {
		try {
			List<UserType> userTypeRetreivedFromDatabase = ofy().load().type(UserType.class).list();
			// if (offersRetreivedFromDatabase.size() != 0) {

			return userTypeRetreivedFromDatabase;
			// }
		} catch (Exception exception) {
			throw exception;
		}
	}

	public static UserType updateUserTypeInDatabase(long userTypeId, UserType userType)
			throws UserPlatformException, Exception {
		try {
			UserType userTypeRetreivedFromDatabase = ofy().load().type(UserType.class).id(userTypeId).now();
			if (userTypeRetreivedFromDatabase != null) {
				userType.setUserTypeId(userTypeId);
				ofy().delete().entities(userTypeRetreivedFromDatabase).now();
				ofy().save().entity(userType).now();
				return userType;
			} else {
				throw new UserTypeException("User Type Status not existing with id : " + userTypeId);
			}
		} catch (Exception exception) {
			throw exception;
		}
	}

	public static UserType deleteUserTypeFromDatabase(long userTypeId) throws UserTypeException, Exception {
		try {
			UserType userTypeRetreivedFromDatabase = ofy().load().type(UserType.class).id(userTypeId).now();
			if (userTypeRetreivedFromDatabase != null) {
				ofy().delete().entities(userTypeRetreivedFromDatabase).now();
				return userTypeRetreivedFromDatabase;
			} else {
				throw new UserTypeException("User Type not existing with id : " + userTypeId);
			}
		} catch (Exception exception) {
			throw exception;
		}
	}

}
// <---------------------------------------------END---------------------------------------------------------------->
