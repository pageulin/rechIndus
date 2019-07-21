package idd.rechercheIndus.application.utils;

public enum MimeType {

	JPG("image/jpeg"),
	PNG("image/png"),
	UNKNOWN("image/jpeg");
	
	private String mimeType;
	
	private MimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	
	public String getMimeType() {
		return this.mimeType;
	}
	
	public static MimeType of(String type) {
		MimeType[] types = MimeType.values();
		for(MimeType mimeType : types) {
			if(mimeType.getMimeType().toLowerCase().equals(type.toLowerCase())) {
				return mimeType;
			}
		}
		return MimeType.UNKNOWN;
	}
}
