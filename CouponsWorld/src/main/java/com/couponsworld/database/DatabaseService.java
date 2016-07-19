package com.couponsworld.database;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;
import java.util.logging.Logger;

import com.couponsworld.dto.Category;
import com.couponsworld.dto.CategorySubCategoryMapping;
import com.couponsworld.dto.Company;
import com.couponsworld.dto.Offer;
import com.couponsworld.dto.SubCategory;
import com.couponsworld.dto.UsabilityStatus;
import com.couponsworld.dto.UserPlatform;
import com.couponsworld.dto.UserType;
import com.couponsworld.exceptions.CategoryException;
import com.couponsworld.exceptions.CategorySubCategoryMappingException;
import com.couponsworld.exceptions.CompanyException;
import com.couponsworld.exceptions.OfferException;
import com.couponsworld.exceptions.SubCategoryException;
import com.couponsworld.exceptions.UsabilityStatusException;
import com.couponsworld.exceptions.UserPlatformException;
import com.couponsworld.exceptions.UserTypeException;
import com.couponsworld.servlets.CategorySubCategoryMappingServlet;
import com.couponsworld.utilities.Constants;
import com.google.api.server.spi.Constant;
import com.googlecode.objectify.ObjectifyService;

public class DatabaseService {

	// declaration of logger
	private static final Logger log = Logger.getLogger(DatabaseService.class.getName());

	static {
		// Registeration of the entities (dto's)
		ObjectifyService.register(Offer.class);
		ObjectifyService.register(Category.class);
		ObjectifyService.register(Company.class);
		ObjectifyService.register(SubCategory.class);
		ObjectifyService.register(UsabilityStatus.class);
		ObjectifyService.register(UserPlatform.class);
		ObjectifyService.register(UserType.class);
		ObjectifyService.register(CategorySubCategoryMapping.class);
	}

	public static long offerNo = Constants.OFFER_NO;
	public static long categoryNo = Constants.CATEGORY_NO;
	public static long companyNo = Constants.COMPANY_NO;
	public static long subCategoryNo = Constants.SUBCATEGORY_NO;
	public static long usabilityStatusNo = Constants.USABILITYSTATUS_NO;
	public static long userPlatformNo = Constants.USERPLATFORM_NO;
	public static long userTypeNo = Constants.USERTYPE_NO;
	public static long categorySubCategoryMappingId = Constants.CATEGORYSUBCATEGORYMAPPING_NO;

	public static CategorySubCategoryMapping createCategorySubCategoryMappingInDatabase(
			CategorySubCategoryMapping categorySubCategoryMapping) throws Exception {
		try {
			log.info("Entered into Database service ..");
			log.info("Saving the categorySubCategoryMapping..");
			categorySubCategoryMapping.setCategorySubCategoryMappingId(++categorySubCategoryMappingId);
			ofy().save().entity(categorySubCategoryMapping);
			log.info("categorySubCategoryMapping saved successfully...");
			log.info("Getting Exit from Database Service ..");
			return categorySubCategoryMapping;
		} catch (Exception exception) {
			log.info("Error in saving categorySubCategoryMapping ..:" + exception.getMessage());
			log.info("Getting Exit from Database Service ..");
			throw exception;
		}
	}

	public static CategorySubCategoryMapping updateCategorySubCategoryMappingInDatabase(
			long categorySubCategoryMappingId, CategorySubCategoryMapping categorySubCategoryMapping)
			throws CategorySubCategoryMappingException, Exception {
		try {

			log.info("Entered into Database service ..");
			log.info("Updating the categorySubCategoryMapping....");
			CategorySubCategoryMapping categorySubCategoryMappingRetreivedFromDatabase = ofy().load()
					.type(CategorySubCategoryMapping.class).id(categorySubCategoryMappingId).now();
			if (categorySubCategoryMappingRetreivedFromDatabase != null) {

				categorySubCategoryMapping.setCategorySubCategoryMappingId(categorySubCategoryMappingId);
				ofy().delete().entities(categorySubCategoryMappingRetreivedFromDatabase).now();
				log.info("Deleted the previous entry for categorySubCategoryMapping");
				ofy().save().entity(categorySubCategoryMapping).now();
				log.info("SAving the updated entry for categorySubCategoryMapping");
				log.info("Getting Exit from the Database Service... ");
				return categorySubCategoryMapping;
			} else {
				log.info("Getting the null Value for categorySubCategoryMapping...");
				log.info("Getting Exit from Database Service...");
				throw new CategorySubCategoryMappingException(
						"Category Sub Category Mapping not existing with Id : " + categorySubCategoryMappingId);
			}
		} catch (Exception exception) {

			log.info("Getting some Error in updating the categorySubCategoryMapping...:" + exception.getMessage());
			log.info("Getting Exit from Database Service...");
			throw exception;
		}
	}

	public static List<CategorySubCategoryMapping> getCategorySubCategoryMappingsFromDatabase()
			throws CategorySubCategoryMappingException, Exception {
		try {
			log.info("Entered into Database service ..");
			log.info("Getting All the categorySubCategoryMapping....");
			List<CategorySubCategoryMapping> categorySubCategoryMappingRetreivedFromDatabase = ofy().load()
					.type(CategorySubCategoryMapping.class).list();
			// if (offersRetreivedFromDatabase.size() != 0) {

			log.info("Got all the CategorySubCategoryMappings Successfully...");
			return categorySubCategoryMappingRetreivedFromDatabase;
			// }
		} catch (Exception exception) {
			log.info("Getting some Error in getting all the categorySubCategoryMappings...:" + exception.getMessage());
			log.info("Getting Exit from Database Service...");
			throw exception;
		}
	}

	public static CategorySubCategoryMapping deleteCategorySubCategoryMappingFromDatabase(
			long categorySubCategoryMappingId) throws CategorySubCategoryMappingException, Exception {
		try {
			log.info("Entered into Database Service...");
			log.info("Deleting the categorySubCategoryMapping from database");
			CategorySubCategoryMapping categorySubCategoryMappingRetreivedFromDatabase = ofy().load()
					.type(CategorySubCategoryMapping.class).id(categorySubCategoryMappingId).now();
			if (categorySubCategoryMappingRetreivedFromDatabase != null) {
				ofy().delete().entities(categorySubCategoryMappingRetreivedFromDatabase).now();
				log.info("categorySubCategoryMapping deleted successfully with corresponding Id passed..");
				return categorySubCategoryMappingRetreivedFromDatabase;
			} else {
				log.info("The categorySubCategoryMappingId passed is null....");
				log.info("Getting Exit from the Database Service...");
				throw new CategorySubCategoryMappingException(
						"Category Sub Category Mapping not existing with Id : " + categorySubCategoryMappingId);
			}
		} catch (Exception exception) {
			log.info("Some Error occured while Deleting the categorySubCategoryMapping from database..:"
					+ exception.getMessage());
			log.info("Getting Exit from the Database Service...");
			throw exception;
		}
	}

	// ----------------------------------------------------------------------------------------------------------
	public static Offer createOfferInDatabase(Offer offer) throws Exception {
		try {
			log.info("Entered into Database Service...");
			log.info("Creating the Offer..");
			offer.setOfferId(++offerNo);
			ofy().save().entity(offer);
			log.info("Offer created Successfully..");
			log.info("Getting Exit from The Database Service..");
			return offer;
		} catch (Exception exception) {
			log.info("Some Error occured while creating the Offer in database..:" + exception.getMessage());
			log.info("Getting Exit from the Database Service...");
			throw exception;
		}
	}

	public static Offer updateOfferInDatabase(long offerId, Offer offer) throws OfferException, Exception {
		try {
			log.info("Entered into Database Service...");
			log.info("Updating the Offer..");
			Offer offerRetreivedFromDatabase = ofy().load().type(Offer.class).id(offerId).now();
			if (offerRetreivedFromDatabase != null) {
				offer.setOfferId(offerId);
				ofy().delete().entities(offerRetreivedFromDatabase).now();
				log.info("Deleted the Offer Existing previously...");
				ofy().save().entity(offer).now();
				log.info("Updating the Offer successfully...");
				return offer;
			} else {
				log.info("Failure in updating the offer as Offer Id passed is null");
				log.info("Getting Exit from the Database Service...");
				throw new OfferException("Offer not existing with offerId : " + offerId);
			}
		} catch (Exception exception) {
			log.info("Some Error occured while updating the Offer in database..:" + exception.getMessage());
			log.info("Getting Exit from the Database Service...");
			throw exception;
		}
	}

	public static List<Offer> getOffersFromDatabase() throws OfferException, Exception {
		try {
			log.info("Entered into Database Service...");
			log.info("Getting the Offers..");
			List<Offer> offersRetreivedFromDatabase = ofy().load().type(Offer.class).list();
			// if (offersRetreivedFromDatabase.size() != 0) {
			log.info("Got all the Offers Successfully from database..");
			return offersRetreivedFromDatabase;
			// }
		} catch (Exception exception) {
			log.info("Some Error occured while getting all the Offers from database..:" + exception.getMessage());
			log.info("Getting Exit from the Database Service...");
			throw exception;
		}
	}

	public static List<Offer> getOffersFromDatabase(String company, String category, String subCategory,
			String userType, String userPlatform, String usabilityStatus, String cashBackMode, String offerType)
			throws OfferException, Exception {
		try {
			log.info("Entered into Database Service...");
			log.info("Getting the Offers.");
			com.googlecode.objectify.cmd.Query<Offer> offersRetreivedFromDatabaseQuery = ofy().load().type(Offer.class);
			if (!(category.equals(""))) {
				log.info("Filtering the OFfers by category passed in the input data ..");
				offersRetreivedFromDatabaseQuery = offersRetreivedFromDatabaseQuery.filter("category", category);
			}
			if (!(company.equals(""))) {
				log.info("Filtering the OFfers by company passed in the input data ..");
				offersRetreivedFromDatabaseQuery = offersRetreivedFromDatabaseQuery.filter("company", company);
			}
			if (!(subCategory.equals(""))) {
				log.info("Filtering the OFfers by sub category passed in the input data ..");
				offersRetreivedFromDatabaseQuery = offersRetreivedFromDatabaseQuery.filter("subCategory", subCategory);
			}
			if (!(userType.equals(""))) {
				log.info("Filtering the OFfers by user type passed in the input data ..");
				offersRetreivedFromDatabaseQuery = offersRetreivedFromDatabaseQuery.filter("userType", userType);
			}
			if (!(userPlatform.equals(""))) {
				log.info("Filtering the OFfers by user platform passed in the input data ..");
				offersRetreivedFromDatabaseQuery = offersRetreivedFromDatabaseQuery.filter("userPlatform",
						userPlatform);
			}
			if (!(usabilityStatus.equals(""))) {
				log.info("Filtering the OFfers by usability Status passed in the input data ..");
				offersRetreivedFromDatabaseQuery = offersRetreivedFromDatabaseQuery.filter("usabilityStatus",
						usabilityStatus);
			}
			if (!(cashBackMode.equals(""))) {
				log.info("Filtering the OFfers by cashback mode passed in the input data ..");
				offersRetreivedFromDatabaseQuery = offersRetreivedFromDatabaseQuery.filter("cashBackMode",
						cashBackMode);
			}
			if (!(offerType.equals(""))) {
				log.info("Filtering the OFfers by offertype passed in the input data ..");
				offersRetreivedFromDatabaseQuery = offersRetreivedFromDatabaseQuery.filter("offerType", offerType);
			}
			List<Offer> offersRetreivedFromDatabaseList = offersRetreivedFromDatabaseQuery.list();

			log.info("Got all the Offers Successfully..");
			// if (offersRetreivedFromDatabase.size() != 0) {

			return offersRetreivedFromDatabaseList;
			// }
		} catch (Exception exception) {
			log.info("Error in getting offers ..:" + exception.getMessage());
			log.info("Getting Exit from Database Service ..");
			throw exception;
		}
	}

	public static Offer deleteOfferFromDatabase(long offerId) throws OfferException, Exception {
		try {
			log.info("Entered into Database Service...");
			log.info("Deleting the Offer...");
			Offer offerRetreivedFromDatabase = ofy().load().type(Offer.class).id(offerId).now();
			if (offerRetreivedFromDatabase != null) {
				ofy().delete().entities(offerRetreivedFromDatabase).now();
				log.info("Offer Deleted Successfully...");
				return offerRetreivedFromDatabase;
			} else {
				log.info("Offer deletion Failure occured as the Offer do not Exist as of corresponding Id ...");
				log.info("Getting Exit from the Database Service..");
				throw new OfferException("Offer not existing with offerId : " + offerId);
			}
		} catch (Exception exception) {
			log.info("Error in deleting offer ..:" + exception.getMessage());
			log.info("Getting Exit from Database Service ..");

			throw exception;
		}
	}

	// Category CRUD --operations
	// --------------------------------------------------------------------------------------------

	public static Category createCategoryInDatabase(Category category) throws Exception {
		try {
			log.info("Enetered into the Database Service...");
			log.info("Creating a new Category..");
			category.setCategoryId(++categoryNo);
			ofy().save().entity(category);
			log.info("Successfully Created the Category into the Database..");
			log.info("Getting Exit from the Database Service..");
			return category;
		} catch (Exception exception) {
			log.info("Error in creating Category ..:" + exception.getMessage());
			log.info("Getting Exit from Database Service ..");

			throw exception;
		}
	}

	public static List<Category> getCategoriesFromDatabase() throws CategoryException, Exception {
		try {
			log.info("Entered into the Database Service....");
			log.info("Getting all the Categories...");
			List<Category> categoriesRetreivedFromDatabase = ofy().load().type(Category.class).list();
			// if (offersRetreivedFromDatabase.size() != 0) {
			log.info("successfully got all the categories...");
			log.info("Getting Exit from the Database Service...");
			return categoriesRetreivedFromDatabase;
			// }
		} catch (Exception exception) {
			log.info("Error in getting all the Categories ..:" + exception.getMessage());
			log.info("Getting Exit from Database Service ..");
			throw exception;
		}
	}

	public static Category updateCategoryInDatabase(long categoryId, Category category)
			throws CategoryException, Exception {
		try {
			log.info("Entered into the Database Service...");
			log.info("Updating the Category...");
			Category categoryRetreivedFromDatabase = ofy().load().type(Category.class).id(categoryId).now();
			if (categoryRetreivedFromDatabase != null) {
				category.setCategoryId(categoryId);
				ofy().delete().entities(categoryRetreivedFromDatabase).now();
				log.info("Deleted the Category existing previously...");
				ofy().save().entity(category).now();
				log.info("saving the new category into the database...");
				log.info("Getting exit from the Database Service");
				return category;
			} else {
				log.info("Category id passed is null");
				log.info("Getting exit from the Database Service");
				throw new CategoryException("Category not existing with category Id : " + categoryId);
			}
		} catch (Exception exception) {
			log.info("Gettting error in updating the Category in database..:" + exception.getMessage());
			log.info("Getting the exit from the Database service..");
			throw exception;
		}
	}

	public static Category deleteCategoryFromDatabase(long categoryId) throws CategoryException, Exception {
		try {
			log.info("Entered into the Database Service...");
			log.info("Deleting the Category...");
			Category categoryRetreivedFromDatabase = ofy().load().type(Category.class).id(categoryId).now();
			if (categoryRetreivedFromDatabase != null) {
				ofy().delete().entities(categoryRetreivedFromDatabase).now();
				log.info("Category deleted successfully..");
				log.info("Getting exit from the Database Service ...");
				return categoryRetreivedFromDatabase;
			} else {
				log.info("Category id passed is null...");
				log.info("Getting exit from the Database Service");

				throw new CategoryException("Category not existing with category Id : " + categoryId);
			}
		} catch (Exception exception) {
			log.info("Getting error in deleting the category..:" + exception.getMessage());
			log.info("Getting exit from the Database Service");
			throw exception;
		}
	}

	// --------------------------------------------------------------------------------------------

	public static Company createCompanyInDatabase(Company company) throws Exception {
		try {
			log.info("Entered into the Database Service...");
			log.info("Creating the Company...");
			company.setCompanyId(++companyNo);
			ofy().save().entity(company);
			log.info("Company created successfully...");
			log.info("Getting exit from the Database Service");
			return company;
		} catch (Exception exception) {
			log.info("Getting error in Creating the Company..:" + exception.getMessage());
			log.info("Getting exit from the Database Service");
			throw exception;
		}
	}

	public static List<Company> getCompaniesFromDatabase() throws CompanyException, Exception {
		try {
			log.info("Entered into the Database Service...");
			log.info("Getting all the Companies.....");
			List<Company> companiesRetreivedFromDatabase = ofy().load().type(Company.class).list();
			// if (offersRetreivedFromDatabase.size() != 0) {
			log.info("Company fetched successfully...");
			log.info("Getting exit from the Database Service");
			return companiesRetreivedFromDatabase;
			// }
		} catch (Exception exception) {
			log.info("Getting error in getting the Companies..:" + exception.getMessage());
			log.info("Getting exit from the Database Service");
			throw exception;
		}
	}

	public static Company updateCompanyInDatabase(long companyId, Company company) throws CompanyException, Exception {
		try {
			log.info("Entered into the Database Service...");
			log.info("updating the Company.....");
			Company companyRetreivedFromDatabase = ofy().load().type(Company.class).id(companyId).now();
			if (companyRetreivedFromDatabase != null) {
				log.info("Getting company value !=null ");
				company.setCompanyId(companyId);
				ofy().delete().entities(companyRetreivedFromDatabase).now();
				log.info("Deleteing the previous company");
				ofy().save().entity(company).now();
				log.info("Saving the new company...");
				return company;
			} else {
				log.info("Company not existing with company Id : " + companyId);
				log.info("Getting exit from the Database Service");
				throw new CompanyException("Company not existing with company Id : " + companyId);
			}
		} catch (Exception exception) {
			log.info("Getting error in updating the Company.......:" + exception.getMessage());
			log.info("Getting exit from the Database Service");
			throw exception;
		}
	}

	public static Company deleteCompanyFromDatabase(long companyId) throws CompanyException, Exception {
		try {
			log.info("Entered into the Database Service...");
			log.info("deleting the Company.....");
			Company companyRetreivedFromDatabase = ofy().load().type(Company.class).id(companyId).now();
			if (companyRetreivedFromDatabase != null) {
				log.info("Getting company value !=null ");
				ofy().delete().entities(companyRetreivedFromDatabase).now();
				log.info("company deleted successfully...");
				log.info("Getting exit from the Database Service...");
				return companyRetreivedFromDatabase;
			} else {
				log.info("Company not existing with company Id : " + companyId);
				log.info("Getting exit from the Database Service");
				throw new CompanyException("Company not existing with company Id : " + companyId);
			}
		} catch (Exception exception) {
			log.info("Getting error in deleting the Company.......:" + exception.getMessage());
			log.info("Getting exit from the Database Service");
			throw exception;
		}
	}

	// --------------------------------------------------------------------------------------------

	public static SubCategory createSubCategoryInDatabase(SubCategory subCategory) throws Exception {
		try {
			log.info("Entered into the Database Service...");
			log.info("creating the Sub Category.....");
			subCategory.setSubCategoryId(++subCategoryNo);
			ofy().save().entity(subCategory);
			log.info("Sub Category Created successsfully...");
			return subCategory;
		} catch (Exception exception) {
			log.info("Getting error in Creating the Sub Category.......:" + exception.getMessage());
			log.info("Getting exit from the Database Service");
			throw exception;
		}
	}

	public static List<SubCategory> getSubCategoriesFromDatabase() throws SubCategoryException, Exception {
		try {
			log.info("Entered into the Database Service...");
			log.info("fetching the Sub Category.....");
			List<SubCategory> subCategoriesRetreivedFromDatabase = ofy().load().type(SubCategory.class).list();
			// if (offersRetreivedFromDatabase.size() != 0) {
			log.info("Sub categories fetched successfully...");
			log.info("Getting exit from the Database Service..");
			return subCategoriesRetreivedFromDatabase;
			// }
		} catch (Exception exception) {
			log.info("Getting error in fetching the sub categories.......:" + exception.getMessage());
			log.info("Getting exit from the Database Service");
			throw exception;
		}
	}

	public static SubCategory updateSubCategoryInDatabase(long subCategoryId, SubCategory subCategory)
			throws SubCategoryException, Exception {
		try {
			log.info("Entered into the Database Service...");
			log.info("updating the Sub Category.....");
			SubCategory subCategoryRetreivedFromDatabase = ofy().load().type(SubCategory.class).id(subCategoryId).now();
			if (subCategoryRetreivedFromDatabase != null) {
				log.info("Sub categories updated successfully...");
				log.info("Getting exit from the Database Service..");
				subCategory.setSubCategoryId(subCategoryId);
				ofy().delete().entities(subCategoryRetreivedFromDatabase).now();
				log.info("Previous Sub Category deleted successfully...");
				ofy().save().entity(subCategory).now();
				log.info("New Sub Category created successfully");
				log.info("Getting the exit from the Database Service..");
				return subCategory;
			} else {
				log.info("Sub Category not existing with subcategoryId : " + subCategoryId);
				log.info("Getting exit from the Database Service...");
				throw new SubCategoryException("Sub Category not existing with subcategoryId : " + subCategoryId);
			}
		} catch (Exception exception) {
			log.info("Getting error in updating the sub category.......:" + exception.getMessage());
			log.info("Getting exit from the Database Service");
			throw exception;
		}
	}

	public static SubCategory deleteSubCategoryFromDatabase(long subCategoryId) throws SubCategoryException, Exception {
		try {
			log.info("Entered into the Database Service...");
			log.info("deleting  the Sub Category.....");
			SubCategory subCategoryRetreivedFromDatabase = ofy().load().type(SubCategory.class).id(subCategoryId).now();
			if (subCategoryRetreivedFromDatabase != null) {
				ofy().delete().entities(subCategoryRetreivedFromDatabase).now();
				log.info("Sub category deleted successfully...");
				log.info("Getting exit from the Database Service..");
				return subCategoryRetreivedFromDatabase;
			} else {
				log.info("Sub Category not existing with subcategoryId : " + subCategoryId);
				log.info("Getting exit from the Database Service...");
				throw new SubCategoryException("Sub Category not existing with subCategoryId : " + subCategoryId);
			}
		} catch (Exception exception) {
			log.info("Getting error in deleting the sub category.......:" + exception.getMessage());
			log.info("Getting exit from the Database Service");
			throw exception;
		}
	}

	// ---------------------------------------------------------------------------------------------

	public static UsabilityStatus createUsabilityStatusInDatabase(UsabilityStatus usabilityStatus) throws Exception {
		try {
			log.info("Entered into the Database Service...");
			log.info("creating  the Usability Status.....");
			usabilityStatus.setUsabilityStatusId(++usabilityStatusNo);
			ofy().save().entity(usabilityStatus);
			log.info("Usability Status created successfully...");
			log.info("Getting exit from the Database Service..");
			return usabilityStatus;
		} catch (Exception exception) {
			log.info("Getting error in deleting the Usability Status.......:" + exception.getMessage());
			log.info("Getting exit from the Database Service");
			throw exception;
		}
	}

	public static List<UsabilityStatus> getUsabilityStatusFromDatabase() throws UsabilityStatusException, Exception {
		try {
			log.info("Entered into the Database Service...");
			log.info("getting all the Usability Status.....");
			List<UsabilityStatus> usabilityStatusRetreivedFromDatabase = ofy().load().type(UsabilityStatus.class)
					.list();
			log.info("getting all the Usability Status.....");
			// if (offersRetreivedFromDatabase.size() != 0) {
			log.info("Getting exit from the Database Service");
			return usabilityStatusRetreivedFromDatabase;
			// }
		} catch (Exception exception) {
			log.info("Getting error in getting all the Usability Status.......:" + exception.getMessage());
			log.info("Getting exit from the Database Service");
			throw exception;
		}
	}

	public static UsabilityStatus updateUsabilityStatusInDatabase(long usabilityStatusId,
			UsabilityStatus usabilityStatus) throws UsabilityStatusException, Exception {
		try {
			log.info("Entered into the Database Service...");
			log.info("updating the Usability Status.....");
			UsabilityStatus usabilityStatusRetreivedFromDatabase = ofy().load().type(UsabilityStatus.class)
					.id(usabilityStatusId).now();
			if (usabilityStatusRetreivedFromDatabase != null) {
				usabilityStatus.setUsabilityStatusId(usabilityStatusId);
				ofy().delete().entities(usabilityStatusRetreivedFromDatabase).now();
				log.info("Deleting previous the usability status...");
				ofy().save().entity(usabilityStatus).now();
				log.info("creating the new usability status...");
				log.info("Getting exit from the Database Service..");
				return usabilityStatus;
			} else {
				log.info("usability status not existing with usability status id : " + usabilityStatusId);
				log.info("Getting exit from the Database Service...");
				throw new UsabilityStatusException("Usability Status not existing with id : " + usabilityStatusId);
			}
		} catch (Exception exception) {
			log.info("Getting error in creating the usability status.......:" + exception.getMessage());
			log.info("Getting exit from the Database Service");
			throw exception;
		}
	}

	public static UsabilityStatus deleteUsabilityStatusFromDatabase(long usabilityStatusId)
			throws UsabilityStatusException, Exception {
		try {
			log.info("Entered into the Database Service...");
			log.info("deleting the Usability Status.....");
			UsabilityStatus usabilityStatusRetreivedFromDatabase = ofy().load().type(UsabilityStatus.class)
					.id(usabilityStatusId).now();
			if (usabilityStatusRetreivedFromDatabase != null) {

				ofy().delete().entities(usabilityStatusRetreivedFromDatabase).now();
				log.info("Usability Status deleted successfully...");
				log.info("Getting exit from the Database Service...");
				return usabilityStatusRetreivedFromDatabase;
			} else {
				log.info("usability status not existing with usability status id : " + usabilityStatusId);
				log.info("Getting exit from the Database Service...");
				throw new UsabilityStatusException("UsabilityStatus not existing with id : " + usabilityStatusId);
			}
		} catch (Exception exception) {
			log.info("Getting error in deleting the Usability Status.......:" + exception.getMessage());
			log.info("Getting exit from the Database Service");
			throw exception;
		}
	}

	// ---------------------------------------------------------------------------------------------

	public static UserPlatform createUserPlatformInDatabase(UserPlatform userPlatform) throws Exception {
		try {
			log.info("Entered into the Database Service...");
			log.info("creating the User Platform.....");
			userPlatform.setUserPlatformId(++userPlatformNo);
			ofy().save().entity(userPlatform);
			log.info("User Platform created successfully...");
			log.info("Getting exit from the Database Service...");
			return userPlatform;
		} catch (Exception exception) {
			log.info("Getting error in creating the User Platform.......:" + exception.getMessage());
			log.info("Getting exit from the Database Service");
			throw exception;
		}
	}

	public static List<UserPlatform> getUserPlatformFromDatabase() throws UserPlatformException, Exception {
		try {
			log.info("Entered into the Database Service...");
			log.info("fetching all the User Platform.....");
			List<UserPlatform> userPlatformRetreivedFromDatabase = ofy().load().type(UserPlatform.class).list();
			// if (offersRetreivedFromDatabase.size() != 0) {

			log.info("User platforms fetched successfully...");
			log.info("Getting exit from the Database Service...");
			return userPlatformRetreivedFromDatabase;
			// }
		} catch (Exception exception) {
			log.info("Getting error in fetching the User Platform.......:" + exception.getMessage());
			log.info("Getting exit from the Database Service");
			throw exception;
		}
	}

	public static UserPlatform updateUserPlatformInDatabase(long userPlatformId, UserPlatform userPlatform)
			throws UserPlatformException, Exception {
		try {
			log.info("Entered into the Database Service...");
			log.info("Updating  the User Platform.....");
			UserPlatform userPlatformRetreivedFromDatabase = ofy().load().type(UserPlatform.class).id(userPlatformId)
					.now();
			if (userPlatformRetreivedFromDatabase != null) {
				userPlatform.setUserPlatformId(userPlatformId);
				ofy().delete().entities(userPlatformRetreivedFromDatabase).now();
				log.info("Deleting the previous User platform");
				ofy().save().entity(userPlatform).now();
				log.info("Creating the new USer platform..");
				log.info("Getting exit from the Database Service");
				return userPlatform;
			} else {
				log.info("User Platform  not existing with id : " + userPlatformId);
				log.info("Getting exit from the Database Service");
				throw new UserPlatformException("User Platform  not existing with id : " + userPlatformId);
			}
		} catch (Exception exception) {
			log.info("Getting error in Updating  the User Platform.......:" + exception.getMessage());
			log.info("Getting exit from the Database Service");
			throw exception;
		}
	}

	public static UserPlatform deleteUserPlatformFromDatabase(long userPlatformId)
			throws UserPlatformException, Exception {
		try {
			log.info("Entered into the Database Service...");
			log.info("deleting the User Platform.....");
			UserPlatform userPlatformRetreivedFromDatabase = ofy().load().type(UserPlatform.class).id(userPlatformId)
					.now();
			if (userPlatformRetreivedFromDatabase != null) {
				ofy().delete().entities(userPlatformRetreivedFromDatabase).now();
				log.info("Üser PLatform Deleted successfully..");
				log.info("Getting the Exit from the Database Service...");
				return userPlatformRetreivedFromDatabase;
			} else {
				log.info("User Platform Status not existing with id : " + userPlatformId);
				log.info("Getting exit from the Database Service");
				throw new UserPlatformException("UserPlatform not existing with id : " + userPlatformId);
			}
		} catch (Exception exception) {
			log.info("Getting error in deleting the User Platform.......:" + exception.getMessage());
			log.info("Getting exit from the Database Service");
			throw exception;
		}
	}

	// ---------------------------------------------------------------------------------------------

	public static UserType createUserTypeInDatabase(UserType userType) throws Exception {
		try {
			log.info("Entered into the Database Service...");
			log.info("creating the User Type.....");
			userType.setUserTypeId(++userTypeNo);
			ofy().save().entity(userType);
			log.info("user type created successfully...");
			return userType;
		} catch (Exception exception) {
			log.info("Getting error in creating the User type.......:" + exception.getMessage());
			log.info("Getting exit from the Database Service");
			throw exception;
		}
	}

	public static List<UserType> getUserTypeFromDatabase() throws UserTypeException, Exception {
		try {
			log.info("Entered into the Database Service...");
			log.info("Getting all the User Type.....");
			List<UserType> userTypeRetreivedFromDatabase = ofy().load().type(UserType.class).list();
			// if (offersRetreivedFromDatabase.size() != 0) {
			log.info("Getting the User types successfully...");
			log.info("Getting the exit from the Database Service...");
			return userTypeRetreivedFromDatabase;
			// }
		} catch (Exception exception) {
			log.info("Getting error in getting all the User type.......:" + exception.getMessage());
			log.info("Getting exit from the Database Service");
			throw exception;
		}
	}

	public static UserType updateUserTypeInDatabase(long userTypeId, UserType userType)
			throws UserPlatformException, Exception {
		try {
			log.info("Entered into the Database Service...");
			log.info("updating the User Type.....");
			UserType userTypeRetreivedFromDatabase = ofy().load().type(UserType.class).id(userTypeId).now();
			if (userTypeRetreivedFromDatabase != null) {
				userType.setUserTypeId(userTypeId);
				ofy().delete().entities(userTypeRetreivedFromDatabase).now();
				log.info("User Type existing previously deleted successfully...");
				ofy().save().entity(userType).now();
				log.info("New User Type created successfully...");
				log.info("Getting the Exit from the Database Service...");
				return userType;
			} else {
				log.info("User Type  not existing with id : " + userTypeId);
				log.info("Getting exit from the Database Service");
				throw new UserTypeException("User Type  not existing with id : " + userTypeId);
			}
		} catch (Exception exception) {
			log.info("Getting error in updating the User type.......:" + exception.getMessage());
			log.info("Getting exit from the Database Service");
			throw exception;
		}
	}

	public static UserType deleteUserTypeFromDatabase(long userTypeId) throws UserTypeException, Exception {
		try {
			log.info("Entered into the Database Service...");
			log.info("deleting the User Type.....");
			UserType userTypeRetreivedFromDatabase = ofy().load().type(UserType.class).id(userTypeId).now();
			if (userTypeRetreivedFromDatabase != null) {
				ofy().delete().entities(userTypeRetreivedFromDatabase).now();
				log.info("User Type deleted successfully..");
				log.info("Getting the Exit from the Database Service...");
				return userTypeRetreivedFromDatabase;
			} else {
				log.info("User Type  not existing with id : " + userTypeId);
				log.info("Getting exit from the Database Service");
				throw new UserTypeException("User Type not existing with id : " + userTypeId);
			}
		} catch (Exception exception) {
			log.info("Getting error in deletings the User type.......:" + exception.getMessage());
			log.info("Getting exit from the Database Service");
			throw exception;
		}
	}

}
// <---------------------------------------------END---------------------------------------------------------------->
