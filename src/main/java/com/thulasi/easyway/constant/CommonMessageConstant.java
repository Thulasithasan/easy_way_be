package com.thulasi.easyway.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommonMessageConstant implements MessageConstant {

	COMMON_ERROR_EMAIL_ALREADY_EXISTS("api.error.common.email-already-exists"),
	COMMON_ERROR_PHONE_NUMBER_ALREADY_EXISTS("api.error.common.phone-number-already-exists"),
	COMMON_ERROR_CHALLENGE_TITLE_ALREADY_EXISTS("api.error.common.challenge-title-already-exists"),
	COMMON_ERROR_CHALLENGE_TYPE_AND_LEVEL_ALREADY_EXISTS("api.error.common.challenge-type-and-level-already-exists"),
	COMMON_ERROR_CHALLENGE_NOT_FOUND("api.error.common.challenge-not-found"),
	COMMON_ERROR_CHALLENGE_CREATED("api.error.common.challenge-created"),
	COMMON_ERROR_CHALLENGE_NOT_ACTIVE("api.error.common.challenge-not-active"),
	COMMON_ERROR_BADGE_NAME_ALREADY_EXISTS("api.error.common.badge-name-already-exists"),
	COMMON_ERROR_BADGE_CRITERIA_TYPE_AND_VALUE_ALREADY_EXISTS(
			"api.error.common.badge-criteria-type-and-value-already-exists"),
	COMMON_ERROR_COMPETITION_NOT_FOUND("api.error.common.competition-not-found"),
	COMMON_ERROR_PAYMENT_NOT_FOUND("api.error.common.payment-not-found"),
	COMMON_ERROR_BANK_ACCOUNT_NOT_FOUND("api.error.common.bank-account-not-found"),
	COMMON_ERROR_COMPETITION_REGISTRATION_NOT_FOUND("api.error.common.competition-registration-not-found"),

	COMMON_ERROR_CATEGORY_NOT_FOUND("api.error.common.category-not-found"),
	COMMON_ERROR_SUBCATEGORY_NOT_FOUND("api.error.common.subcategory-not-found"),
	COMMON_ERROR_PRODUCT_NOT_FOUND("api.error.common.product-not-found"),
	COMMON_ERROR_SALES_ORDER_NOT_FOUND("api.error.common.sales-order-not-found"),
	COMMON_ERROR_PURCHASE_BILL_NOT_FOUND("api.error.common.purchase-bill-not-found"),
	COMMON_ERROR_STOCK_NOT_FOUND("api.error.common.stock-not-found"),
	COMMON_ERROR_CARD_ITEM_NOT_FOUND("api.error.common.card-item-not-found"),
	COMMON_ERROR_CARD_ITEMS_NOT_FOUND("api.error.common.card-items-not-found"),
	COMMON_ERROR_RECURRING_ORDER_ITEM_NOT_FOUND("api.error.common.recurring-order-item-not-found"),
	COMMON_ERROR_RECURRING_ORDER_NOT_FOUND("api.error.common.recurring-order-not-found"),
	COMMON_ERROR_INVOICE_NOT_FOUND("api.error.common.invoice-not-found"),
	COMMON_ERROR_FAVOURITE_ITEM_NOT_FOUND("api.error.common.favourite-item-not-found"),

	// Success message constants
	COMMON_SUCCESS_USER_CREATED("api.success.common.user-created"),
	COMMON_SUCCESS_FILE_UPLOAD("api.success.common.file-upload"),
	COMMON_SUCCESS_ORGANIZATION_CREATE("api.success.common.create-organization"),
	COMMON_DATABASE_RESET_SUCCESS("api.success.common.reset-database"),

	// Error message constants
	COMMON_ERROR_ACCESS_DENIED("api.error.common.access-denied"),
	COMMON_ERROR_UNAUTHORIZED_ACCESS("api.error.common.unauthorized-access"),
	COMMON_ERROR_INVALID_CREDENTIALS("api.error.common.invalid-credentials"),
	COMMON_ERROR_USER_ACCOUNT_DEACTIVATED("api.error.common.user-account-deactivated"),
	COMMON_ERROR_USER_NOT_FOUND("api.error.common.user-not-found"),
	COMMON_ERROR_ENCRYPTION_FAILED("api.error.common.encryption-failed"),
	COMMON_ERROR_DECRYPTION_FAILED("api.error.common.decryption-failed"),
	COMMON_ERROR_ENCRYPTION_DECRYPTION_SET_KEY_FAILED("api.error.common.encryption-decryption-key-failed"),
	COMMON_ERROR_VALIDATION_ERROR("api.error.common.missing-mandatory-fields"),
	COMMON_ERROR_MODULE_EXCEPTION("api.error.common.module-exception"),
	COMMON_ERROR_ENTITY_NOT_FOUND("api.error.common.entity-not-found"),
	COMMON_ERROR_VALIDATION_ENTER_FIRST_NAME("api.error.common.validation.enter-first-name"),
	COMMON_ERROR_VALIDATION_FIRST_NAME_LENGTH("api.error.common.validation.first-name-length"),
	COMMON_ERROR_VALIDATION_FIRST_NAME_SPECIAL_CHAR("api.error.common.validation.first-name-special-char"),
	COMMON_ERROR_VALIDATION_ENTER_LAST_NAME("api.error.common.validation.enter-last-name"),
	COMMON_ERROR_VALIDATION_LAST_NAME_LENGTH("api.error.common.validation.last-name-length"),
	COMMON_ERROR_VALIDATION_LAST_NAME_SPECIAL_CHAR("api.error.common.validation.last-name-special-char"),
	COMMON_ERROR_VALIDATION_EMAIL("api.error.common.validation.email"),
	COMMON_ERROR_VALIDATION_PASSWORD("api.error.common.validation.password"),
	COMMON_ERROR_VALIDATION_PASSWORD_REQUIREMENTS("api.error.common.validation.password-requirements"),
	COMMON_ERROR_DATABASE_ERROR("api.error.common.database-error"),
	COMMON_ERROR_SERVLET_EXCEPTION("api.error.common.servlet-exception"),
	COMMON_ERROR_TOO_MANY_REQUESTS_EXCEPTION("api.error.common.too-many-requests-exception"),
	COMMON_ERROR_IO_EXCEPTION("api.error.common.io-exception"),
	COMMON_ERROR_INVALID_FOLDER_TYPE("api.error.common.invalid-folder-type"),
	COMMON_ERROR_FILE_UPLOAD("api.error.common.file-upload"),
	COMMON_ERROR_INVALID_FILE_TYPE("api.error.common.invalid-file-type"),
	COMMON_ERROR_FILE_NOT_EXIST("api.error.common.file-not-exist"),
	COMMON_ERROR_NO_ENOUGH_STORAGE("api.error.common.storage-exceed"),
	COMMON_ERROR_FILE_URL("api.error.common.file-url"),
	COMMON_ERROR_USER_ACCOUNT_ACTIVATED("api.error.common.user-account-already-activated"),
	COMMON_ERROR_EXCEED_MAX_ORGANIZATION_COUNT("api.error.common.organization-create"),
	COMMON_ERROR_USER_ALREADY_EXISTS("api.error.common.user-already-exists"),
	COMMON_ERROR_SUPER_ADMIN_ALREADY_EXISTS("api.error.common.super-admin-already-exists"),
	COMMON_ERROR_INVALID_REFRESH_TOKEN("api.error.common.refresh-token-invalid"),
	COMMON_ERROR_TIME_CONFIGS_NOT_FOUND("api.error.common.time-configs-not-found"),
	COMMON_ERROR_JSON_STRING_TO_OBJECT_CONVERSION_FAILED("api.error.common.json-object-convert"),
	COMMON_ERROR_TEAM_NOT_FOUND("api.error.common.team-not-found"),
	COMMON_ERROR_USER_IS_NOT_A_TEAM_SUPERVISOR("api.error.common.user-not-team-supervisor"),
	COMMON_ERROR_MANAGER_CANNOT_VIEW_EMPLOYEE_DATA("api.error.common.manager-cannot-view-employee-data"),
	COMMON_ERROR_MANAGER_PERMISSION_NOT_FOUND("api.error.common.manager-permission-not-found"),
	COMMON_ERROR_ALREADY_PASSWORD_RESET("api.error.common.already-password-reset"),
	COMMON_ERROR_TEAM_EMPLOYEE_SUPERVISING_TEAMS("api.error.common.team-employee-supervising-teams"),
	COMMON_ERROR_EMPLOYEE_SUPERVISING_EMPLOYEES("api.error.common.employee-supervising-employees"),
	COMMON_ERROR_EMAIL_CONFIG_NOT_FOUND("api.error.common.email-config-not-found"),
	COMMON_ERROR_CANNOT_USE_PREVIOUS_PASSWORDS("api.error.common.cannot-use-previous-passwords"),
	COMMON_ERROR_NOTIFICATION_NOT_FOUND("api.error.common.notification-not-found"),
	COMMON_ERROR_START_DATE_END_DATE_NOT_VALID("api.error.common.start-date-end-date-not-valid"),
	COMMON_ERROR_OLD_PASSWORD_INCORRECT("api.error.common.old-password-incorrect"),
	COMMON_ERROR_FAILED_TO_CONVERT_DYNAMIC_VALUES("api.error.common.failed-to-convert-dynamic-values"),
	COMMON_ERROR_NOTIFICATION_TEMPLATE_LOADING_FAILED("api.error.common.notification-template-loading-failed"),
	COMMON_ERROR_FAILED_TO_LOAD_NOTIFICATION_LANGUAGE("api.error.common.failed-to-load-notification-language"),
	COMMON_ERROR_TEMPLATE_NOT_FOUND_FOR_LANGUAGE("api.error.common.template-not-found-for-language"),
	COMMON_ERROR_TEMPLATE_ID_NOT_FOUND("api.error.common.template-id-not-found"),
	COMMON_ERROR_NOTIFICATION_TEMPLATE_PARSING_FAILED("api.error.common.notification-template-parsing-failed"),
	COMMON_ERROR_VALIDATION_EMPLOYEE_NAME("api.error.common.validation.employee-name"),
	COMMON_ERROR_VALIDATION_EMAIL_LENGTH("api.error.common.validation.email-length"),
	COMMON_ERROR_VALIDATION_CITY_STATE("api.error.common.validation.city-state"),
	COMMON_ERROR_VALIDATION_SSN("api.error.common.validation.ssn"),
	COMMON_ERROR_VALIDATION_NAME("api.error.common.validation.name"),
	COMMON_ERROR_VALIDATION_NAME_LENGTH("api.error.common.validation.name-length"),
	COMMON_ERROR_VALIDATION_EMERGENCY_CONTACT_NAME("api.error.common.validation.emergency-contact-name"),
	COMMON_ERROR_VALIDATION_BIRTH_DATE("api.error.common.validation.birth-date"),
	COMMON_ERROR_VALIDATION_PHONE_NUMBER("api.error.common.validation.phone-number"),
	COMMON_ERROR_VALIDATION_PHONE_NUMBER_LENGTH("api.error.common.validation.phone-number-length"),
	COMMON_ERROR_VALIDATION_EMERGENCY_CONTACT_PHONE_NUMBER(
			"api.error.common.validation.emergency-contact-phone-number"),
	COMMON_ERROR_VALIDATION_ADDRESS("api.error.common.validation.address"),
	COMMON_ERROR_VALIDATION_ADDRESS_LENGTH("api.error.common.validation.address-length"),
	COMMON_ERROR_VALIDATION_STATE_PROVINCE("api.error.common.validation.state-province"),
	COMMON_ERROR_VALIDATION_IDENTIFICATION_NUMBER("api.error.common.validation.identification-number"),
	COMMON_ERROR_VALIDATION_IDENTIFICATION_NUMBER_LENGTH("api.error.common.validation.identification-number-length"),
	COMMON_ERROR_VALIDATION_NIN("api.error.common.validation.nin"),
	COMMON_ERROR_VALIDATION_NIN_LENGTH("api.error.common.validation.nin-length"),
	COMMON_ERROR_VALIDATION_JOIN_DATE("api.error.common.validation.join-date"),
	COMMON_ERROR_VALIDATION_START_DATE("api.error.common.validation.start-date"),
	COMMON_ERROR_VALIDATION_END_DATE("api.error.common.validation.end-date"),
	COMMON_ERROR_VALIDATION_START_DATE_JOIN_DATE("api.error.common.validation.start-date-join-date"),
	COMMON_ERROR_VALIDATION_START_DATE_END_DATE("api.error.common.validation.start-date-end-date"),
	COMMON_ERROR_VALIDATION_VISA_ISSUED_DATE("api.error.common.validation.visa-issued-date"),
	COMMON_ERROR_VALIDATION_VISA_EXPIRATION_DATE("api.error.common.validation.visa-expiration-date"),
	COMMON_ERROR_VALIDATION_ENTER_INSTITUTION("api.error.common.validation.enter-institution"),
	COMMON_ERROR_VALIDATION_ENTER_DEGREE("api.error.common.validation.enter-degree"),
	COMMON_ERROR_VALIDATION_ENTER_SPECIALIZATION("api.error.common.validation.enter-specialization"),
	COMMON_ERROR_ORGANIZATION_TIMEZONE_FORMAT_INVALID("api.error.common.organization.timezone-format-invalid"),
	COMMON_ERROR_ORGANIZATION_THEME_COLOR_FORMAT_INVALID("api.error.common.organization.theme-color-format-invalid"),
	COMMON_ERROR_EXCEED_MAX_EMPLOYEE_COUNT("api.error.common.exceeded-max-employee-count"),
	COMMON_ERROR_VALIDATION_PASSPORT("api.error.common.validation.passport"),
	COMMON_ERROR_VALIDATION_PASSPORT_LENGTH("api.error.common.validation.passport-length"),
	COMMON_ERROR_TOKEN_EXPIRED("api.error.common.token-expired"),
	COMMON_ERROR_REFRESH_TOKEN_NOT_ALLOWED("api.error.common.refresh-token-not-allowed"),
	COMMON_ERROR_INVALID_TOKEN("api.error.common.invalid-token"),
	COMMON_ERROR_INTERNAL_SERVER_ERROR("api.error.common.internal-server-error"),
	COMMON_ERROR_MAX_RETRIES_REACHED("api.error.common.max-retries-reached"),

	ERROR_DATE_CANNOT_BE_NULL("api.error.common.date-cannot-be-null"),
	ERROR_DATE_TIME_CANNOT_BE_NULL("api.error.common.date-time-cannot-be-null"),
	ERROR_TIME_CANNOT_BE_NULL("api.error.common.time-cannot-be-null"),
	ERROR_TIME_ZONE_CANNOT_BE_NULL("api.error.common.time-zone-cannot-be-null"),
	ERROR_START_END_DATE_CANNOT_BE_NULL("api.error.common.start-end-date-cannot-be-null"),
	ERROR_INVALID_DAY_OF_MONTH("api.error.common.invalid-day-of-month"),
	ERROR_EPOCH_MILLIS_CANNOT_BE_NULL("api.error.common.epoch-millis-cannot-be-null");

	private final String messageKey;

}
