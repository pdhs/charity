package lk.gov.health.charity.jsf;

import lk.gov.health.charity.entity.FamilyUnit;
import lk.gov.health.charity.jsf.util.JsfUtil;
import lk.gov.health.charity.jsf.util.JsfUtil.PersistAction;
import lk.gov.health.charity.facelets.FamilyUnitFacade;

import java.io.Serializable;
import java.lang.reflect.Member;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import lk.gov.health.charity.entity.FamilyMember;
import lk.gov.health.charity.facelets.MembersFacade;

@ManagedBean(name = "familyUnitController")
@SessionScoped
public class FamilyUnitController implements Serializable {

    @EJB
    private lk.gov.health.charity.facelets.FamilyUnitFacade ejbFacade;
    @EJB
    MembersFacade membersFacade;
    private List<FamilyUnit> items = null;
    private FamilyUnit selected;

    FamilyMember addingFamilyMember;

    public FamilyUnitController() {
    }

    public FamilyMember getAddingFamilyMember() {
        if (addingFamilyMember == null) {
            addingFamilyMember = new FamilyMember();
        }
        return addingFamilyMember;
    }

    public void addFamilyMemberToSavedOne() {
        System.out.println("addFamilyMemberToSavedOne");
        if (selected == null) {
            System.out.println("selected null");
            JsfUtil.addErrorMessage("Family?");
            return;
        }
        System.out.println("selected = " + selected);
        System.out.println("selected.getMembers() = " + selected.getMembers());
        System.out.println("selected.getMembers().size() = " + selected.getMembers().size());
        int size = selected.getMembers().size() + 1;
        System.out.println("1");
        addingFamilyMember.setFamilyMemberOrderNo(Long.valueOf(size));
        System.out.println("2");
        addingFamilyMember.setFamilyUnit(selected);
        System.out.println("3");
        selected.getMembers().add(addingFamilyMember);
        System.out.println("4");
        addingFamilyMember = new FamilyMember();
    }

    public void addFamilyMemberToNewOne() {
        if (selected == null) {
            JsfUtil.addErrorMessage("Family?");
            return;
        }
        create();
        int size = selected.getMembers().size() + 1;
        addingFamilyMember.setFamilyMemberOrderNo(Long.valueOf(size));
        addingFamilyMember.setFamilyUnit(selected);
        selected.getMembers().add(addingFamilyMember);
        addingFamilyMember = new FamilyMember();
    }

    public void setAddingFamilyMember(FamilyMember addingFamilyMember) {
        this.addingFamilyMember = addingFamilyMember;
    }

    public FamilyUnit getSelected() {
        return selected;
    }

    public void setSelected(FamilyUnit selected) {
        addingFamilyMember = new FamilyMember();
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private FamilyUnitFacade getFacade() {
        return ejbFacade;
    }

    public FamilyUnit prepareCreate() {
        selected = new FamilyUnit();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        if (selected.getId() == null || selected.getId() == 0) {
            persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle1").getString("FamilyUnitCreated"));
        } else {
            persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle1").getString("FamilyUnitCreated"));
        }
        for (FamilyMember m : selected.getMembers()) {
            System.out.println("m = " + m.getNameSin());
            System.out.println("m = " + m.getId());
            m.setFamilyUnit(selected);
            if (m.getId() == null || m.getId() == 0) {
                membersFacade.create(m);
            } else {
                membersFacade.edit(m);
            }
        }
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle1").getString("FamilyUnitUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle1").getString("FamilyUnitDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<FamilyUnit> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction == PersistAction.CREATE) {
                    getFacade().create(selected);
                } else if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle1").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle1").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<FamilyUnit> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<FamilyUnit> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = FamilyUnit.class)
    public static class FamilyUnitControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FamilyUnitController controller = (FamilyUnitController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "familyUnitController");
            return controller.getFacade().find(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof FamilyUnit) {
                FamilyUnit o = (FamilyUnit) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), FamilyUnit.class.getName()});
                return null;
            }
        }

    }

}
