package alararestaurant.service;


import alararestaurant.domain.dtos.importJSON.ItemDto;
import alararestaurant.domain.entities.Category;
import alararestaurant.domain.entities.Item;
import alararestaurant.repository.CategoryRepository;
import alararestaurant.repository.ItemRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ItemServiceImpl implements ItemService {

    private static final String ITEMS_IMPORT_JSON_FILE_PATH =
            "D:\\Java\\Hibernate\\AlaraRestaurant\\AlaraRestaurant\\src\\main\\resources\\files\\items.json";
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, CategoryRepository categoryRepository, FileUtil fileUtil, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean itemsAreImported() {
        return this.itemRepository.count() > 0;

    }

    @Override
    public String readItemsJsonFile() throws IOException {
        return this.fileUtil.readFile(ITEMS_IMPORT_JSON_FILE_PATH);
    }

    @Override
    public String importItems(String items) {
        StringBuilder sb = new StringBuilder();
        ItemDto[] itemsList = this.gson.fromJson(items, ItemDto[].class);
        for (ItemDto itemDto : itemsList) {

            Item itemExists = this.itemRepository.findItemByName(itemDto.getName());

            if (!this.validationUtil.isValid(itemDto) || itemExists != null) {
                sb.append("Invalid data format.").append(System.lineSeparator());
                continue;
            }

            Category category = this.categoryRepository.findCategoriesByName(itemDto.getCategory());
            if (category == null) {
                category = new Category(itemDto.getCategory());
            }
            Item item = this.modelMapper.map(itemDto, Item.class);
            item.setCategory(category);
            this.itemRepository.saveAndFlush(item);
            sb.append(String.format("Record %s successfully imported.%n", item.getName()));
        }
        return sb.toString().trim();
    }
}
