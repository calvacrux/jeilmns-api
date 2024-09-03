package jeilm.api.cmm.util;

public class FilterUtil {

	/**
     * XSS 인코딩
     *
     * @param value
     * @return String
     */
    public static String encodeXSS(String inputValue){
        
    	String returnValue = inputValue;
    	    	
    	// PMD규칙에 따라 수정 - 파라미터 값을 직접 변경하지 말것
    	//returnValue = returnValue.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
        returnValue = returnValue.replaceAll("\0", "");
        returnValue = returnValue.replaceAll("'", "&#39;");
        returnValue = returnValue.replaceAll("\\(", "&#40;");
        returnValue = returnValue.replaceAll("\\)", "&#41;");
        returnValue = returnValue.replaceAll("--", "&#45;&#45;");
        returnValue = returnValue.replaceAll("eval\\((.*)\\)", "");
        returnValue = returnValue.replaceAll("[\\\"\\\'][\\s]*javascript:(.*, arg1)[\\\"\\\']", "\"\"");
        
        returnValue = returnValue.replaceAll("(?i)script", "scr-ipt");
        returnValue = returnValue.replaceAll("(?i)iframe", "i-frame");
        returnValue = returnValue.replaceAll("(?i)frameset", "frame-set");
        returnValue = returnValue.replaceAll("(?i)applet", "apple&#116;");
        
        returnValue = returnValue.replaceAll("(?i)javascript", "java-script");
        returnValue = returnValue.replaceAll("(?i)vbscript", "vb-script");
        returnValue = returnValue.replaceAll("(?i)onactivate", "on-activate");
        returnValue = returnValue.replaceAll("(?i)on-abort", "onabort");
        returnValue = returnValue.replaceAll("(?i)onafterprint", "on-afterprint");
        returnValue = returnValue.replaceAll("(?i)onafterupdate", "on-afterupdate");
        returnValue = returnValue.replaceAll("(?i)onbeforeactivate", "on-beforeactivate");
        returnValue = returnValue.replaceAll("(?i)onbeforecopy", "on-beforecopy");
        returnValue = returnValue.replaceAll("(?i)onbeforecut", "on-beforecut");
        returnValue = returnValue.replaceAll("(?i)onbeforedeactivate", "on-beforedeactivate");
        returnValue = returnValue.replaceAll("(?i)onbeforeeditfocus", "on-beforeeditfocus");
        returnValue = returnValue.replaceAll("(?i)onbeforepaste", "on-beforepaste");
        returnValue = returnValue.replaceAll("(?i)onbeforeunload", "on-beforeunload");
        returnValue = returnValue.replaceAll("(?i)onbeforeupdate", "on-beforeupdate");
        returnValue = returnValue.replaceAll("(?i)onblur", "on-blur");
        returnValue = returnValue.replaceAll("(?i)onbounce", "on-bounce");
        returnValue = returnValue.replaceAll("(?i)onbegin", "on-begin");
        returnValue = returnValue.replaceAll("(?i)oncanplay", "on-canplay");
        returnValue = returnValue.replaceAll("(?i)oncellchange", "on-cellchange");
        returnValue = returnValue.replaceAll("(?i)onchange", "on-change");
        returnValue = returnValue.replaceAll("(?i)onclick", "on-click");
        returnValue = returnValue.replaceAll("(?i)oncontextmenu", "on-contextmenu");
        returnValue = returnValue.replaceAll("(?i)oncontrolselect", "on-controlselect");
        returnValue = returnValue.replaceAll("(?i)oncopy", "on-copy");
        returnValue = returnValue.replaceAll("(?i)oncut", "on-cut");
        returnValue = returnValue.replaceAll("(?i)oncontentready", "on-contentready");
        returnValue = returnValue.replaceAll("(?i)oncontentsave", "on-contentsave");
        returnValue = returnValue.replaceAll("(?i)ondataavailable", "on-dataavailable");
        returnValue = returnValue.replaceAll("(?i)ondatasetchanged", "on-datasetchanged");
        returnValue = returnValue.replaceAll("(?i)ondatasetcomplete", "on-datasetcomplete");
        returnValue = returnValue.replaceAll("(?i)ondblclick", "on-dblclick");
        returnValue = returnValue.replaceAll("(?i)ondeactivate", "on-deactivate");
        returnValue = returnValue.replaceAll("(?i)ondetach", "on-detach");
        returnValue = returnValue.replaceAll("(?i)ondocumentready", "on-documentready");
        returnValue = returnValue.replaceAll("(?i)ondrag", "on-drag");
        returnValue = returnValue.replaceAll("(?i)ondragend", "on-dragend");
        returnValue = returnValue.replaceAll("(?i)ondragenter", "on-dragenter");
        returnValue = returnValue.replaceAll("(?i)ondragleave", "on-dragleave");
        returnValue = returnValue.replaceAll("(?i)ondragover", "on-dragover");
        returnValue = returnValue.replaceAll("(?i)ondragstart", "on-dragstart");
        returnValue = returnValue.replaceAll("(?i)ondragdrop", "on-dragdrop");
        returnValue = returnValue.replaceAll("(?i)ondrop", "on-drop");
        returnValue = returnValue.replaceAll("(?i)ondurationchange", "on-durationchange");
        returnValue = returnValue.replaceAll("(?i)onemptied", "on-emptied");
        returnValue = returnValue.replaceAll("(?i)onended", "on-ended");
        returnValue = returnValue.replaceAll("(?i)onerrorupdate", "on-errorupdate");
        returnValue = returnValue.replaceAll("(?i)onfilterchange", "on-filterchange");
        returnValue = returnValue.replaceAll("(?i)onfinish", "on-finish");
        returnValue = returnValue.replaceAll("(?i)onfocus", "on-focus");
        returnValue = returnValue.replaceAll("(?i)onfocusin", "on-focusin");
        returnValue = returnValue.replaceAll("(?i)onfocusout", "on-focusout");
        returnValue = returnValue.replaceAll("(?i)onhashchange", "on-hashchange");
        returnValue = returnValue.replaceAll("(?i)onhelp", "on-help");
        returnValue = returnValue.replaceAll("(?i)onhide", "on-hide");
        returnValue = returnValue.replaceAll("(?i)oninput", "on-input");
        returnValue = returnValue.replaceAll("(?i)onkeydown", "on-keydown");
        returnValue = returnValue.replaceAll("(?i)onkeypress", "on-keypress");
        returnValue = returnValue.replaceAll("(?i)onkeyup", "on-keyup");
        returnValue = returnValue.replaceAll("(?i)onlayoutcomplete", "on-layoutcomplete");
        returnValue = returnValue.replaceAll("(?i)onload", "on-load");
        returnValue = returnValue.replaceAll("(?i)onloadeddata", "on-loadeddata");
        returnValue = returnValue.replaceAll("(?i)onloadedmetadata", "on-loadedmetadata");
        returnValue = returnValue.replaceAll("(?i)onloadstart", "on-loadstart");
        returnValue = returnValue.replaceAll("(?i)onlosecapture", "on-losecapture");
        returnValue = returnValue.replaceAll("(?i)onmessage", "on-message");
        returnValue = returnValue.replaceAll("(?i)onmediacomplete", "on-mediacomplete");
        returnValue = returnValue.replaceAll("(?i)onmediaerror", "on-mediaerror");
        returnValue = returnValue.replaceAll("(?i)onmedialoadfailed", "on-medialoadfailed");
        returnValue = returnValue.replaceAll("(?i)onmousedown", "on-mousedown");
        returnValue = returnValue.replaceAll("(?i)onmouseenter", "on-mouseenter");
        returnValue = returnValue.replaceAll("(?i)onmouseleave", "on-mouseleave");
        returnValue = returnValue.replaceAll("(?i)onmousemove", "on-mousemove");
        returnValue = returnValue.replaceAll("(?i)onmouseout", "on-mouseout");
        returnValue = returnValue.replaceAll("(?i)onmouseover", "on-mouseover");
        returnValue = returnValue.replaceAll("(?i)onmouseup", "on-mouseup");
        returnValue = returnValue.replaceAll("(?i)onmousewheel", "on-mousewheel");
        returnValue = returnValue.replaceAll("(?i)onmove", "on-move");
        returnValue = returnValue.replaceAll("(?i)onmoveend", "on-moveend");
        returnValue = returnValue.replaceAll("(?i)onmovestart", "on-movestart");
        returnValue = returnValue.replaceAll("(?i)onoffline", "on-offline");
        returnValue = returnValue.replaceAll("(?i)ononline", "on-online");
        returnValue = returnValue.replaceAll("(?i)ononopenstatechange", "on-openstatechange");
        returnValue = returnValue.replaceAll("(?i)ononoutofsync", "on-outofsync");
        returnValue = returnValue.replaceAll("(?i)onpage", "on-page");
        returnValue = returnValue.replaceAll("(?i)onerror", "on-error");
        returnValue = returnValue.replaceAll("(?i)onpaste", "on-paste");
        returnValue = returnValue.replaceAll("(?i)onpause", "on-pause");
        returnValue = returnValue.replaceAll("(?i)onplay", "on-play");
        returnValue = returnValue.replaceAll("(?i)onplaying", "on-playing");
        returnValue = returnValue.replaceAll("(?i)onplaystatechange", "on-playstatechange");
        returnValue = returnValue.replaceAll("(?i)onprogress", "on-progress");
        returnValue = returnValue.replaceAll("(?i)onratechange", "on-ratechange");
        returnValue = returnValue.replaceAll("(?i)onpropertychange", "on-propertychange");
        returnValue = returnValue.replaceAll("(?i)onreadystatechange", "on-readystatechange");
        returnValue = returnValue.replaceAll("(?i)onrepeat", "on-repeat");
        returnValue = returnValue.replaceAll("(?i)onresume", "on-resume");
        returnValue = returnValue.replaceAll("(?i)onreset", "on-reset");
        returnValue = returnValue.replaceAll("(?i)onresize", "on-resize");
        returnValue = returnValue.replaceAll("(?i)onresizeend", "on-resizeend");
        returnValue = returnValue.replaceAll("(?i)onresizestart", "on-resizestart");
        returnValue = returnValue.replaceAll("(?i)onreverse", "on-reverse");
        returnValue = returnValue.replaceAll("(?i)onrowclick", "on-rowclick");
        returnValue = returnValue.replaceAll("(?i)onrowout", "on-rowout");
        returnValue = returnValue.replaceAll("(?i)onrowenter", "on-rowenter");
        returnValue = returnValue.replaceAll("(?i)onrowover", "on-rowover");
        returnValue = returnValue.replaceAll("(?i)onrowdelete", "on-rowdelete");
        returnValue = returnValue.replaceAll("(?i)onrowexit", "on-rowexit");
        returnValue = returnValue.replaceAll("(?i)onrowsdelete", "on-rowsdelete");
        returnValue = returnValue.replaceAll("(?i)onrowsinserted", "on-rowsinserted");
        returnValue = returnValue.replaceAll("(?i)onsave", "on-save");
        returnValue = returnValue.replaceAll("(?i)onseek", "on-seek");
        returnValue = returnValue.replaceAll("(?i)onscroll", "on-scroll");
        returnValue = returnValue.replaceAll("(?i)onseeked", "on-seeked");
        returnValue = returnValue.replaceAll("(?i)onseeking", "on-seeking");
        returnValue = returnValue.replaceAll("(?i)onselect", "on-select");
        returnValue = returnValue.replaceAll("(?i)onselectionchange", "on-selectionchange");
        returnValue = returnValue.replaceAll("(?i)onselectstart", "on-selectstart");
        returnValue = returnValue.replaceAll("(?i)onshow", "on-show");
        returnValue = returnValue.replaceAll("(?i)onstalled", "on-stalled");
        returnValue = returnValue.replaceAll("(?i)onstart", "on-start");
        returnValue = returnValue.replaceAll("(?i)onstop", "on-stop");
        returnValue = returnValue.replaceAll("(?i)onstorage", "on-storage");
        returnValue = returnValue.replaceAll("(?i)onstoragecommit", "on-storagecommit");
        returnValue = returnValue.replaceAll("(?i)onsubmit", "on-submit");
        returnValue = returnValue.replaceAll("(?i)onsuspend", "on-suspend");
        returnValue = returnValue.replaceAll("(?i)onsyncrestored", "on-syncrestored");
        returnValue = returnValue.replaceAll("(?i)ontimeerror", "on-timeerror");
        returnValue = returnValue.replaceAll("(?i)ontimeout", "on-timeout");
        returnValue = returnValue.replaceAll("(?i)ontimeupdate", "on-timeupdate");
        returnValue = returnValue.replaceAll("(?i)ontrackchange", "on-trackchange");
        returnValue = returnValue.replaceAll("(?i)onunload", "on-unload");
        returnValue = returnValue.replaceAll("(?i)onurlflip", "on-urlflip");
        returnValue = returnValue.replaceAll("(?i)onvolumechange", "on-volumechange");
        returnValue = returnValue.replaceAll("(?i)onwaiting", "on-waiting");
        
        return returnValue;  
    }
    
    
    /**
     * HTMLTagFilter로 인코딩된 텍스트 디코딩
     *
     * @param value
     * @return
     */
    public static String decodeHTML(String inputValue){
        String returnValue = inputValue;
        
        returnValue = returnValue.replaceAll("&amp;", "&");
        returnValue = returnValue.replaceAll("&lt;", "<");
        returnValue = returnValue.replaceAll("&gt;", ">");
        returnValue = returnValue.replaceAll("&quot;", "\"");
        returnValue = returnValue.replaceAll("&#39;", "\'");
        returnValue = returnValue.replaceAll("&#45;&#45;", "--");
        returnValue = returnValue.replaceAll("&#40;", "\\(");
        returnValue = returnValue.replaceAll("&#41;", "\\)");
        
        return returnValue;
    }
    
    /**
     * 패스워드 특수 문자 입력 했을때만 디코딩 하는 함수
     * @param value
     * @return
     */
    public static String decodePW(String inputValue){
    	String returnValue = inputValue;
        
    	returnValue = returnValue.replaceAll("&#35;", "#");
        returnValue = returnValue.replaceAll("&#36;", "$");
        returnValue = returnValue.replaceAll("&#37;", "%");
        returnValue = returnValue.replaceAll("&amp;", "&");
        returnValue = returnValue.replaceAll("&lt;", "<");
        returnValue = returnValue.replaceAll("&gt;", ">");
        returnValue = returnValue.replaceAll("&quot;", "\"");
        returnValue = returnValue.replaceAll("&#39;", "\'");
        returnValue = returnValue.replaceAll("&#45;&#45;", "--");
        returnValue = returnValue.replaceAll("&#45;", "-");
        returnValue = returnValue.replaceAll("&#40;", "\\(");
        returnValue = returnValue.replaceAll("&#41;", "\\)");
        returnValue = returnValue.replaceAll("&#63;", "?");
        returnValue = returnValue.replaceAll("&#59;", ";");
        returnValue = returnValue.replaceAll("&#123;", "{");
        returnValue = returnValue.replaceAll("&#125;", "}");
        returnValue = returnValue.replaceAll("&#124;", "|");
        
        return returnValue;
    }
    
}
